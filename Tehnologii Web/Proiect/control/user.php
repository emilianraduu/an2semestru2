<?php
class User
{
    private $email;
    private $password;
    private $fname;
    private $lname;
    private $avatar;
    private $phone;

    function __construct($email, $password, $fname, $lname, $avatar, $phone)
    {
        $this->email = $email;
        $this->password = $password;
        $this->fname = $fname;
        $this->lname = $lname;
        $this->avatar = $avatar;
        $this->phone = $phone;
    }
    function setAvatar($avatar){
        $this->avatar = $avatar;
    }

    function getEmail()
    {
        return $this->email;
    }
    function getPassword()
    {
        return $this->password;
    }
    function getFname()
    {
        return $this->fname;
    }
    function getLname()
    {
        return $this->lname;
    }
    function getAvatar()
    {
        return $this->avatar;
    }
    function getPhone()
    {
        return $this->phone;
    }
    function generateRandomString($length = 10) {
        return substr(str_shuffle(str_repeat($x='0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', ceil($length/strlen($x)) )),1,$length);
    }
    function startSession($db){
        session_start();
        $sessionID = $this->generateRandomString();
        $_SESSION['SID'] = $sessionID;
        $_SESSION['UID'] = $db->getId($this);
    }
}

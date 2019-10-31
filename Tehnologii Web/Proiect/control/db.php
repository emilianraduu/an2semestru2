<?php
class Database
{
    private $address = '127.0.0.1';
    private $user = 'lostpets_account';
    private $password = 'pass';
    private $database = 'lost_pets';
    public $con = null;

    public function __construct()
    {
        $this->con = mysqli_connect($this->address, $this->user, $this->password, $this->database);
    }
    public function getCon()
    {
        return $this->con;
    }

    public function register($user)
    {
        $query = $this->getCon()->prepare("INSERT INTO user (id_user, mail, lname, fname, password, avatar, phone) VALUES(DEFAULT,?,?,?,?,?,?)");
        $query->bind_param("ssssss", $user->getEmail(), $user->getLname(), $user->getFname(), $user->getPassword(), $user->getAvatar(), $user->getPhone());
        if ($query->execute()) {
            $query->close();
            return true;
        } else {
            $query->close();
            return false;
        }
    }

    public function formular($pet)
    {
        $query = $this->getCon()->prepare("INSERT INTO pet (id_pet, gallery, location, name, species, breed, details, reward) VALUES(DEFAULT,?,?,?,?,?,?,?)");
        $query->bind_param("sssssss", $pet->getGallery(), $pet->getLocation(), $pet->getName(), $pet->getSpecies(), $pet->getBreed(), $pet->getDetails(), $pet->getReward());
        if ($query->execute()) {
            $query->close();
            return true;
        } else {
            $query->close();
            return false;
        }
    }

    public function login($email, $password)
    {
        $query = $this->getCon()->prepare("SELECT * FROM user WHERE mail = ? AND password = ?");
        $query->bind_param("ss", $email, $password);
        $query->execute();
        $query->bind_result($id_user, $mail, $lname, $fname, $password, $avatar, $phone);
        $query->store_result();
        if ($query->fetch()) {
            $temp = new User($mail, $password, $fname, $lname, $avatar, $phone);
            return $temp;
        } else {
            return null;
        }
    }

    public function getJSONUser($id)
    {
        $query = $this->getCon()->prepare("SELECT mail,lname,fname,avatar,phone FROM user WHERE id_user LIKE ?");
        $query->bind_param("s", $id);
        $query->execute();
        $query->bind_result($mail, $lname, $fname, $avatar, $phone);
        $query->store_result();
        if ($query->fetch()) {
            $temp = [
        "mail" => $mail,
        "fname" => $fname,
        "lname" => $lname,
        "avatar" => $avatar,
        "phone" => $phone
      ];
            return json_encode($temp);
        } else {
            return null;
        }
    }


    public function updateNotification($id_user, $id_pet)
    {
        $query = $this->getCon()->prepare("UPDATE notifications SET isRead=1 WHERE id_user LIKE ? AND id_pet LIKE ? AND isRead=0");
        $query->bind_param("ss", $id_user, $id_pet);
        $query->execute();
        if($query->fetch());
    }
    public function insertNotification($owner, $id_user, $id_pet)
    {
        $query = $this->getCon()->prepare("INSERT INTO notifications (id_user, id_pet, id_owner, isRead) VALUES (?,?,?,DEFAULT)");
        $query->bind_param("sss", $id_user, $id_pet, $owner);
        $query->execute();
    }
    public function getNotifications($id)
    {
        $query = $this->getCon()->prepare("SELECT p.id_pet, p.name, u.fname, u.lname, u.id_user FROM notifications n JOIN pet p ON p.id_pet = n.id_pet JOIN owner o ON o.id_pet=p.id_pet JOIN user u ON u.id_user=o.id_user WHERE isRead=0 AND n.id_owner LIKE ?");
        $query->bind_param("s", $id);
        $query->execute();
        $query->bind_result($id_pet, $name, $fname, $lname, $id);
        $query->store_result();
        $data = [];
        while ($query->fetch()) {
            array_push($data, [
            "id_pet" => $id_pet,
            "fname" => $fname,
            "name" => $name,
            "id_user" => $id,
            "lname" => $lname
          ]);
        }
        return $data;
    }

    public function getUser($id)
    {
        $query = $this->getCon()->prepare("SELECT * FROM user WHERE id_user LIKE ?");
        $query->bind_param("s", $id);
        $query->execute();
        $query->bind_result($id_user, $mail, $lname, $fname, $password, $avatar, $phone);
        $query->store_result();
        if ($query->fetch()) {
            $temp = new User($mail, $password, $fname, $lname, $avatar, $phone);
            return $temp;
        } else {
            return null;
        }
    }

    public function unactivated($id_user)
    {
        $query = $this->getCon()->prepare("INSERT INTO verify (id_user,	activate_code, activated) VALUES ((SELECT id_user FROM user WHERE id_user LIKE ?),?, DEFAULT)");
        $temp = $this->generateRandomString();
        $query->bind_param("is", $id_user, $temp);
        $query->execute();
        return $temp;
    }

    public function getId($user)
    {
        $query = $this->getCon()->prepare("SELECT id_user FROM user WHERE mail LIKE ?");
        $query->bind_param("s", $user->getEmail());
        $query->execute();
        $query->bind_result($id_user);
        $query->store_result();
        if ($query->fetch()) {
            return $id_user;
        } else {
            return null;
        }
    }

    public function insertPet($pet, $user)
    {
        $query = $this->getCon()->prepare("INSERT INTO pet (id_pet,gallery,location,name,species,breed,details,reward) VALUES (DEFAULT,?,?,?,?,?,?,?)");
        $query->bind_param("sssssss", $pet->getGallery(), $pet->getLocation(), $pet->getName(), $pet->getSpecies(), $pet->getBreed(), $pet->getDetails(), $pet->getReward());
        if ($query->execute()) {
            $query1 = $this->getCon()->prepare("INSERT INTO owner (id_user, id_pet) VALUES ((SELECT id_user FROM user WHERE mail LIKE ?), (SELECT id_pet FROM pet WHERE gallery LIKE ?))");
            $query1->bind_param("ss", $user->getEmail(), $pet->getGallery());
            $query1->execute();

            $query2 = $this->getCon()->prepare("SELECT id_pet FROM pet WHERE gallery LIKE ?");
            $query2->bind_param("s", $pet->getGallery());
            $query2->execute();
            $query2->bind_result($id_pet);
            $query2->store_result();
            if ($query2->fetch()) {
                $query3 = $this->getCon()->prepare("SELECT id_user FROM user WHERE mail LIKE ?");
                $query3->bind_param("s", $user->getEmail());
                $query3->execute();
                $query3->bind_result($id_user);
                $query3->store_result();
                if ($query3->fetch()) {
                    $this->foundPet($id_user, $id_pet, $pet->getLocation());
                }
            }
            // return true;
      // } else return false;
        }
    }

    public function foundPet($id_user, $id_pet, $location)
    {
        $query = $this->getCon()->prepare("INSERT INTO found (id_user, id_pet, location, found) VALUES (?,?,?,DEFAULT)");
        $query->bind_param("sss", $id_user, $id_pet, $location);
        if ($query->execute()) {
            return true;
        } else {
            return false;
        }
    }

    public function updateFoundPet($id_user, $id_pet, $location)
    {
        $query = $this->getCon()->prepare("UPDATE found SET found=1 WHERE id_user LIKE ? and id_pet LIKE ? and location like ?");
        $query->bind_param("sss", $id_user, $id_pet, $location);
        if ($query->execute()) {
            return true;
        } else {
            return false;
        }
    }

    // function getLastPets()
    // {
    //   $query = $this->getCon()->prepare("SELECT a.id_pet FROM (SELECT id_pet FROM pet ORDER BY id_pet DESC) a WHERE row_num<3");

    //   $query->execute();
    //   $query->bind_result($id_pet);
    //   $query->store_result();
    //   $i = 0;
    //   while ($query->fetch())
    //     echo $id_pet . " ". $i;
    //     $i = $i+1;
    // }


    public function getAnimal($id)
    {
        $query = $this->getCon()->prepare("SELECT p.id_pet, p.gallery, p.location, p.name, p.species, p.breed, p.details, p.reward, u.mail, u.phone, u.fname, u.avatar, u.id_user FROM pet p JOIN owner o ON o.id_pet=p.id_pet JOIN user u ON u.id_user=o.id_user WHERE p.id_pet LIKE ?");

        $query->bind_param("s", $id);

        $query->execute();
        $query->bind_result($id_pet, $gallery, $location, $name, $species, $breed, $details, $reward, $email, $phone, $lname, $avatar, $id);
        $query->store_result();
        $temp = [];
        while ($query->fetch()) {
            array_push($temp, [
        "id" => $id_pet,
        "gallery" => $gallery,
        "location" => $location,
        "name" => $name,
        "species" => $species,
        "breed" => $breed,
        "details" => $details,
        "reward" => $reward,
        "mail" => $email,
        "phone" => $phone,
        "lname" => $lname,
        "avatar" => $avatar,
        "uid" => $id,
        "users" => $this->getFounds($id_pet)
      ]);
            // $a = $this->getFounds($id_pet);
      // echo $a;
        }
        return $temp;
    }
    public function getFounds($id)
    {
        $query = $this->getCon()->prepare("SELECT f.id_user, found,location,avatar,fname,lname FROM found f JOIN user u ON u.id_user=f.id_user WHERE id_pet LIKE ?");
        $query->bind_param("s", $id);
        $query->execute();
        $query->bind_result($id_user, $found, $location, $avatar, $fname, $lname);
        $array = [];
        while ($query->fetch()) {
            array_push($array, [
        "id" => $id_user,
        "location" => $location,
        "found" => $found,
        "avatar" => $avatar,
        "fname" => $fname,
        "lname" => $lname
      ]);
        }
        return $array;
    }
    public function getAnimals($user)
    {
        $query = $this->getCon()->prepare("SELECT id_pet FROM owner WHERE id_user LIKE ?");
        $query->bind_param("s", $user);
        $query->execute();
        $query->bind_result($id_pet);
        $query->store_result();
        $pets = [];
        while ($query->fetch()) {
            // if ($this->found($id_pet) != null) {
            array_push($pets, [$this->getPetById($id_pet), $this->getFoundAnimals($user)]);
            // }
        }
        return $pets;
    }
    public function getFoundAnimals($id_user)
    {
        $query = $this->getCon()->prepare("SELECT f.id_pet,gallery FROM found f JOIN pet p ON p.id_pet=f.id_pet WHERE f.id_user LIKE ? AND found=1");
        $query->bind_param("s", $id_user);
        $query->execute();
        $pets = [];
        $query->bind_result($id_pet, $gallery);
        while ($query->fetch()) {
            // if ($this->found($id_pet) != null) {
            array_push($pets, [$id_pet, $gallery]);
            // }
        }
        return $pets;
    }
    public function getPetById($id_pet)
    {
        $query = $this->getCon()->prepare("SELECT * FROM pet WHERE id_pet LIKE ?");
        $query->bind_param("s", $id_pet);
        $query->execute();
        $pets = [];
        $query->bind_result($id_pet, $gallery, $location, $name, $species, $breed, $details, $reward);
        if ($query->fetch()) {
            $temp = [
        "id" => $id_pet,
        "gallery" => $gallery,
        "location" => $location,
        "name" => $name,
        "species" => $species,
        "breed" => $breed,
        "details" => $details,
        "reward" => $reward
      ];
        }
        return $temp;
    }
    public function getAllAnimals()
    {
        $query = $this->getCon()->prepare("SELECT * FROM pet");
        $query->execute();
        $query->bind_result($id_pet, $gallery, $location, $name, $species, $breed, $details, $reward);
        $query->store_result();
        $pets = [];

        while ($query->fetch()) {
            array_push($pets, [
        "id" => $id_pet,
        "gallery" => $gallery,
        "location" => $location,
        "name" => $name,
        "species" => $species,
        "breed" => $breed,
        "details" => $details,
        "reward" => $reward
      ]);
        }
        return $pets;
    }

    public function getNearPets($lat, $lng)
    {
        // $location = $this->getUserLocation($id);
        $pets = $this->getAllAnimals();
        $aroundPets = [];
        foreach ($pets as $pet) {
            $temp = explode(" ", $pet["location"]);
            $lat = $temp[0];
            $long = $temp[1];
            $d = $this->distance($lat, $long, $lat, $lng, "K");

            if ($this->found1($pet['id']) != null) {
                if ($d < 10) {
                    array_push($aroundPets, $pet);
                }
            }
        }
        return $aroundPets;
    }

    public function isFound($id_pet)
    {
        $query = $this->getCon()->prepare("SELECT found FROM found WHERE id_pet like ?");
        $query->bind_param("s", $id_pet);
        $query->execute();
        $query->bind_result($found);
        $query->store_result();
        while ($query->fetch()) {
            if ($found == 1) {
                return false;
            }
        }
        return true;
    }
    public function found1($id)
    {
        $foundPeople = [];
        $query = $this->getCon()->prepare("SELECT id_user,location,found FROM found WHERE id_pet like ?");
        $query->bind_param("s", $id);
        $query->execute();
        $query->bind_result($id_user, $location, $found);
        $query->store_result();
        while ($query->fetch()) {
            if ($this->isFound($id)) {
                array_push($foundPeople, [
          "id" => $id_user,
          "location" => $location,
          "found" => $found
        ]);
            }
        }
        return $foundPeople;
    }
    public function found($id)
    {
        $foundPeople = [];
        $query = $this->getCon()->prepare("SELECT id_user,location,found FROM found WHERE id_pet like ?");
        $query->bind_param("s", $id);
        $query->execute();
        $query->bind_result($id_user, $location, $found);
        $query->store_result();
        while ($query->fetch()) {
            if ($found == 1) {
                array_push($foundPeople, [
          "id" => $id_user,
          "location" => $location,
          "found" => $found
        ]);
            }
        }
        return $foundPeople;
    }

    public function distance($lat1, $lon1, $lat2, $lon2, $unit)
    {
        $theta = $lon1 - $lon2;
        $dist = sin(deg2rad($lat1)) * sin(deg2rad($lat2)) +  cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * cos(deg2rad($theta));
        $dist = acos($dist);
        $dist = rad2deg($dist);
        $miles = $dist * 60 * 1.1515;
        $unit = strtoupper($unit);

        if ($unit == "K") {
            return ($miles * 1.609344);
        } elseif ($unit == "N") {
            return ($miles * 0.8684);
        } else {
            return $miles;
        }
    }

    public function getUserLocation($id)
    {
        $query = $this->getCon()->prepare("SELECT lat,lng FROM location WHERE id_user LIKE ?");

        $query->bind_param("s", $id);
        $query->execute();
        $query->bind_result($lat, $lng);
        $query->store_result();
        if ($query->fetch()) {
            $obj = [
        "lat" => $lat,
        "lng" => $lng
      ];
        }
        return $obj;
    }

    public function updateLocation($obj)
    {
        $ok = 1;
        $query1 = $this->getCon()->prepare("SELECT id_user FROM location WHERE id_user LIKE ?");
        $query1->bind_param("s", $obj->id);
        $query1->execute();
        $query1->bind_result($id_user);
        while ($query1->fetch()) {
            if ($id_user != null) {
                $ok = 0;
            }
        }

        if ($ok == 1) {
            $query = $this->getCon()->prepare("INSERT INTO location (id_user, lat, lng) VALUES ((SELECT id_user FROM user WHERE id_user LIKE ?), ?, ?)");
            $query->bind_param("sss", $obj->id, $obj->lat, $obj->lng);
            $query->execute();
        }
        if ($ok  == 0) {
            $query = $this->getCon()->prepare("UPDATE location SET lat=?, lng=? WHERE id_user LIKE ?");
            $query->bind_param("sss", $obj->lat, $obj->lng, $obj->id);
            $query->execute();
        }
    }

    public function queryStatistics($isFound)
    {
        $query = $this->getCon()->prepare("SELECT p.species, p.breed,count(*) FROM pet p JOIN found f ON f.id_pet=p.id_pet WHERE found = ? GROUP BY p.breed,p.species");
        $query->bind_param('s', $isFound);
        $query->execute();
        $results = [];
        $result = $query->get_result();
        while ($row = $result->fetch_object()) {
            $results[] = $row;
        }
        return $results;
    }
    public function getStatistics()
    {
        $pets = [];
        $pets[]=["lost" => $this->queryStatistics(0)];
        $pets[]=["found" => $this->queryStatistics(1)];
    
        return $pets;
    }

    public function verify($user = null, $code = 0)
    {
        if ($user == null) {
            return false;
        }
        $query = $this->getCon()->prepare("SELECT id_user, activate_code, activated FROM verify WHERE id_user LIKE ?");
        $query->bind_param("i", $this->getId($user));
        $query->execute();
        $query->bind_result($id_user, $activate_code, $activated);
        $query->store_result();

        if ($query->fetch()) {
            if ($activated == 1) {
                return true;
            } else {
                if ($activate_code === $code) {
                    $query1 = $this->getCon()->prepare("UPDATE verify SET activated = 1 WHERE id_user LIKE ?");
                    $query1->bind_param("s", $id_user);
                    $query1->execute();
                } else {
                    return false;
                }
            }
        }
    }

    public function generateRandomString($length = 10)
    {
        return substr(str_shuffle(str_repeat($x = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', ceil($length / strlen($x)))), 1, $length);
    }
}

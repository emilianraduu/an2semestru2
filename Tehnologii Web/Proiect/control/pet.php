<?php
class Pet
{
    private $gallery;
    private $location;
    private $name;
    private $species;
    private $breed;
    private $details;
    private $reward;

    function __construct($gallery, $location, $name, $species, $breed, $details, $reward)
    {
        $this->gallery = $gallery;
        $this->location = $location;
        $this->name = $name;
        $this->species = $species;
        $this->breed = $breed;
        $this->details = $details;
        $this->reward = $reward;
    }

    function getGallery()
    {
        return $this->gallery;
    }
    function getLocation()
    {
        return $this->location;
    }
    function getName()
    {
        return $this->name;
    }
    function getSpecies()
    {
        return $this->species;
    }
    function getBreed()
    {
        return $this->breed;
    }
    function getDetails()
    {
        return $this->details;
    }
    function getReward()
    {
        return $this->reward;
    }
}
?>
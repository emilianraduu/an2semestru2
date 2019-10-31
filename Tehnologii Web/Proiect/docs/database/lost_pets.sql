-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 18, 2019 at 08:16 PM
-- Server version: 5.7.25
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `lost_pets`
--

-- --------------------------------------------------------

--
-- Table structure for table `verify`
--

CREATE TABLE `verify` (
  `id_user` int(11) NOT NULL,
  `activate_code` varchar(30) NOT NULL,
  `activated` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `verify`
--

INSERT INTO `verify` (`id_user`, `activate_code`, `activated`) VALUES
(109, 'l1rT9LsRAK', 1),
(110, 'SEInOZdzNu', 1),
(111, '8FEJrZjadG', 1),
(112, 'vOoQLKChuw', 1),
(113, 'gNZdSE6zVH', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `verify`
--
ALTER TABLE `verify`
  ADD UNIQUE KEY `id_user` (`id_user`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `verify`
--
ALTER TABLE `verify`
  ADD CONSTRAINT `fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE;

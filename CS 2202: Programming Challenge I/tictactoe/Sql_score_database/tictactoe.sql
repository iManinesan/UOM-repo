-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2014 at 08:23 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tictactoe`
--

-- --------------------------------------------------------

--
-- Table structure for table `multiplayer`
--

CREATE TABLE IF NOT EXISTS `multiplayer` (
  `Player1` int(11) DEFAULT NULL,
  `Player2` int(11) DEFAULT NULL,
  UNIQUE KEY `Player2` (`Player2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `multiplayer`
--

INSERT INTO `multiplayer` (`Player1`, `Player2`) VALUES
(0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `single_player`
--

CREATE TABLE IF NOT EXISTS `single_player` (
  `Player` int(11) DEFAULT NULL,
  `Computer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `single_player`
--

INSERT INTO `single_player` (`Player`, `Computer`) VALUES
(0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

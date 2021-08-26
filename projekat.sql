-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 21, 2021 at 01:38 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projekat`
--

-- --------------------------------------------------------

--
-- Table structure for table `artikal`
--

CREATE TABLE `artikal` (
  `id` int(11) NOT NULL,
  `kubikaza` varchar(128) NOT NULL,
  `snaga` int(11) DEFAULT NULL,
  `cena` varchar(128) NOT NULL,
  `opis` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(2000) DEFAULT NULL,
  `model_id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `artikal`
--

INSERT INTO `artikal` (`id`, `kubikaza`, `snaga`, `cena`, `opis`, `image`, `model_id`, `id_user`) VALUES
(37, '2000', 190, '27500', 'Triggers aspiration: the BMW 1 Series has arrived to attract all the admiring glances. This is because its outstanding design and progressive design language distinguish it from the crowd at the very first glance. The same holds true for its interior with a modern atmosphere that inspires through premium quality and openness. Efficient engines, the front-wheel drive now premiering in the BMW 1 Series and state-of-the-art chassis technologies guarantee driving experiences full of dynamics and agility, while innovative technologies and driver assistance systems ensure that you always reach your destination safely and comfortably.', 'https://www.bmw.rs/content/bmw/marketB4R1/bmw_rs/sr_RS/all-models/1-series/5-door/2019/inspire/jcr:content/par/highlight_b887/highlightitems/highlightitem_81b5/image/mobile.transform/highlight/image.1567430561471.jpg', 34, 27),
(41, '1200', 110, '27500', 'We have expanded our hybrid battery warranty to reflect our confidence in the quality, dependability and reliability of our products. From the 2020 model year forward, every Toyota hybrid battery warranty will cover 10 years from date of first use or 150,000 miles, whichever comes first (previously 8 years or 100,000 miles).', 'https://www.toyota.com/imgix/responsive/images/gallery/photos-videos/2021/priusprime/PRP_MY20_0018_V001.png?w=2880', 45, 27);

-- --------------------------------------------------------

--
-- Table structure for table `marka`
--

CREATE TABLE `marka` (
  `id_marka` int(11) NOT NULL,
  `naziv` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `marka`
--

INSERT INTO `marka` (`id_marka`, `naziv`) VALUES
(12, 'Audi'),
(11, 'BMW'),
(15, 'Mazda'),
(13, 'Porshe'),
(14, 'Skoda'),
(16, 'Toyota');

-- --------------------------------------------------------

--
-- Table structure for table `model`
--

CREATE TABLE `model` (
  `id_model` int(11) NOT NULL,
  `naziv_modela` varchar(128) DEFAULT NULL,
  `marka_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `model`
--

INSERT INTO `model` (`id_model`, `naziv_modela`, `marka_id`) VALUES
(34, '120d', 11),
(35, 'A3', 12),
(36, '330d', 11),
(38, 'B120', 13),
(41, 'Fabia', 14),
(42, 'A4', 12),
(44, 'Oktavia', 14),
(45, 'Auris', 16),
(46, '6', 15);

-- --------------------------------------------------------

--
-- Table structure for table `narudzbenica`
--

CREATE TABLE `narudzbenica` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_artikla` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `narudzbenica`
--

INSERT INTO `narudzbenica` (`id`, `id_user`, `id_artikla`) VALUES
(33, 27, 37);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `adresa` varchar(100) DEFAULT NULL,
  `email` varchar(70) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(512) NOT NULL,
  `role` varchar(20) DEFAULT 'user',
  `phone` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `ime`, `prezime`, `adresa`, `email`, `username`, `password`, `role`, `phone`) VALUES
(27, 'David', 'Mitic', 'Neka tamo', 'david@test.com', 'admin', '123', 'admin', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `artikal`
--
ALTER TABLE `artikal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_model` (`model_id`),
  ADD KEY `fk_user` (`id_user`);

--
-- Indexes for table `marka`
--
ALTER TABLE `marka`
  ADD PRIMARY KEY (`id_marka`),
  ADD UNIQUE KEY `naziv` (`naziv`);

--
-- Indexes for table `model`
--
ALTER TABLE `model`
  ADD PRIMARY KEY (`id_model`),
  ADD KEY `fk_marka` (`marka_id`);

--
-- Indexes for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_iduser` (`id_user`),
  ADD KEY `fk_idartikal` (`id_artikla`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `artikal`
--
ALTER TABLE `artikal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `marka`
--
ALTER TABLE `marka`
  MODIFY `id_marka` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `model`
--
ALTER TABLE `model`
  MODIFY `id_model` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `artikal`
--
ALTER TABLE `artikal`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Constraints for table `model`
--
ALTER TABLE `model`
  ADD CONSTRAINT `fk_marka` FOREIGN KEY (`marka_id`) REFERENCES `marka` (`id_marka`);

--
-- Constraints for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  ADD CONSTRAINT `fk_idartikal` FOREIGN KEY (`id_artikla`) REFERENCES `artikal` (`id`),
  ADD CONSTRAINT `fk_iduser` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

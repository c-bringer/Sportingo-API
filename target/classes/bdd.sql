-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 16, 2021 at 07:25 AM
-- Server version: 5.7.26
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `sportingo`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
                            `id` int(11) NOT NULL,
                            `label` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `label`) VALUES
(1, 'Poussin'),
(2, 'Pupilles'),
(3, 'Bennjamin'),
(4, 'Minime'),
(5, 'Cadet'),
(6, 'Junior'),
(7, 'Espoir'),
(8, 'Senior'),
(9, 'Master'),
(10, 'Poussin'),
(11, 'Pupilles'),
(12, 'Bennjamin'),
(13, 'Minime'),
(14, 'Cadet'),
(15, 'Junior'),
(16, 'Espoir'),
(17, 'Senior'),
(18, 'Master');

-- --------------------------------------------------------

--
-- Table structure for table `difficulte`
--

CREATE TABLE `difficulte` (
                              `id` int(11) NOT NULL,
                              `label` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `difficulte`
--

INSERT INTO `difficulte` (`id`, `label`) VALUES
(1, 'Très facile'),
(2, 'Facile'),
(3, 'Moyen'),
(4, 'Difficile'),
(5, 'Très difficile'),
(6, 'Extrême'),
(7, 'Très facile'),
(8, 'Facile'),
(9, 'Moyen'),
(10, 'Difficile'),
(11, 'Très difficile'),
(12, 'Extrême');

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

CREATE TABLE `evenement` (
                         `id` int(11) NOT NULL,
                         `image` varchar(255) DEFAULT NULL,
                         `name` varchar(50) NOT NULL,
                         `description` text NOT NULL,
                         `date_hours` datetime NOT NULL,
                         `minimum_birthday` date NOT NULL,
                         `number_places` int(11) NOT NULL,
                         `longitude` double NOT NULL,
                         `latitude` double NOT NULL,
                         `id_user` int(11) NOT NULL,
                         `id_mechanical_sport` int(11) NOT NULL,
                         `is_verified` tinyint(1) NOT NULL,
                         `is_disabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id`, `image`, `name`, `description`, `date_hours`, `minimum_birthday`, `number_places`, `longitude`, `latitude`, `id_user`, `id_mechanical_sport`, `is_verified`, `is_disabled`) VALUES
(1, '', 'Natural Games', 'Gravida dictumst pharetra nullam interdum augue lectus iaculis nec.', '2021-01-30 15:00:00', '2000-01-01', 100, -130.6867, -55.727, 4, 1, 1, 0),
(2, '[djfhdf.png]', 'TMT Cup', 'Litora augue rhoncus fames venenatis sed auctor praesent ligula nulla.', '2021-01-30 14:30:00', '2005-01-01', 100, -130.6867, -55.727, 4, 4, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `event_category`
--

CREATE TABLE `event_category` (
                                  `id_event` int(11) NOT NULL,
                                  `id_category` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `event_category`
--

INSERT INTO `event_category` (`id_event`, `id_category`) VALUES
(1, 2),
(1, 3),
(1, 4),
(2, 5),
(2, 6);

-- --------------------------------------------------------

--
-- Table structure for table `mechanical_sport`
--

CREATE TABLE `mechanical_sport` (
                                    `id` int(11) NOT NULL,
                                    `label` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `mechanical_sport`
--

INSERT INTO `mechanical_sport` (`id`, `label`) VALUES
(1, 'VTT XC'),
(2, 'VTT de descente'),
(3, 'VTT d\'enduro'),
(4, 'Vélo trial'),
(5, 'BMX race'),
(6, 'Motocross'),
(7, 'Moto d\'enduro'),
(8, 'Moto de route'),
(9, 'Moto trial'),
(10, 'Voiture'),
(11, 'VTT XC'),
(12, 'VTT de descente'),
(13, 'VTT d\'enduro'),
(14, 'Vélo trial'),
(15, 'BMX race'),
(16, 'Motocross'),
(17, 'Moto d\'enduro'),
(18, 'Moto de route'),
(19, 'Moto trial'),
(20, 'Voiture');

-- --------------------------------------------------------

--
-- Table structure for table `spot`
--

CREATE TABLE `spot` (
                        `id` int(11) NOT NULL,
                        `image` varchar(255) DEFAULT NULL,
                        `youtube_link` varchar(255) DEFAULT NULL,
                        `name` varchar(50) NOT NULL,
                        `description` text NOT NULL,
                        `longitude` double NOT NULL,
                        `latitude` double NOT NULL,
                        `is_verified` tinyint(1) NOT NULL,
                        `is_disabled` tinyint(1) NOT NULL,
                        `id_user` int(11) NOT NULL,
                        `id_difficulty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spot`
--

INSERT INTO `spot` (`id`, `image`, `youtube_link`, `name`, `description`, `longitude`, `latitude`, `is_verified`, `is_disabled`, `id_user`, `id_difficulty`) VALUES
(1, '[njdnf.png, jndfjs.jpg]', 'https://www.youtube.com/watch?v=Fo8RICmCGzg', 'Carbassas', 'Tortor suspendisse tellus nisi sem commodo quam sodales turpis.', -120.0507, -14.4443, 1, 0, 1, 4),
(2, '', 'https://www.youtube.com/watch?v=JF8BRvqGCNs', 'Bresse', 'Sed libero interdum lacus suscipit volutpat nostra tincidunt.', 100.7865, 79.3177, 1, 0, 1, 1),
(3, '', 'https://www.youtube.com/watch?v=0KSOMA3QBU0', 'Cresse', 'Vivamus augue tellus amet scelerisque dictumst sociosqu.', 131.2142, 54.5523, 1, 1, 3, 3),
(4, '[djfhdf.png]', '', 'Boundalou', 'Litora augue rhoncus fames venenatis sed auctor praesent ligula nulla.', -130.6867, -55.727, 0, 0, 8, 2),
(5, '', 'https://www.youtube.com/watch?v=OPf0YbXqDm0', 'Cathédrales', 'Gravida dictumst pharetra nullam interdum augue lectus iaculis nec.', -56.5488, 17.7802, 0, 0, 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `spot_mechanical_sport`
--

CREATE TABLE `spot_mechanical_sport` (
                                         `id_mechanical_sport` int(11) NOT NULL,
                                         `id_spot` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spot_mechanical_sport`
--

INSERT INTO `spot_mechanical_sport` (`id_mechanical_sport`, `id_spot`) VALUES
(3, 1),
(1, 2),
(4, 3),
(6, 4),
(2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
                        `id` int(11) NOT NULL,
                        `civilite` varchar(8) NOT NULL,
                        `prenom` varchar(50) NOT NULL,
                        `nom` varchar(50) NOT NULL,
                        `date_naissance` date DEFAULT NULL,
                        `email` varchar(100) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `is_admin` tinyint(1) NOT NULL,
                        `is_desactive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `civilite`, `prenom`, `nom`, `date_naissance`, `email`, `password`, `is_admin`, `is_desactive`) VALUES
(1, 'Monsieur', 'Corentin', 'Bringer', '2000-10-08', 'corentinbringer@ik.me', '$2y$10$LzToNkmKlZMdH6O0Ngol0uYw807xEvB4flqCE/bjmrVW/u7Diy6TK', 1, 0),
(2, 'Monsieur', 'Chrissy', 'Mcmanus', '1997-10-10', 'oliver.goldner44@yahoo.com', '$2y$10$LzToNkmKlZMdH6O0Ngol0uYw807xEvB4flqCE/bjmrVW/u7Diy6TK', 0, 0),
(3, 'Monsieur', 'Setsuko', 'Carpenter', '1998-10-25', 'may_johnson0@hotmail.com', '$2y$10$LzToNkmKlZMdH6O0Ngol0uYw807xEvB4flqCE/bjmrVW/u7Diy6TK', 0, 0),
(4, 'Monsieur', 'Bo', 'Holding', '2019-01-13', 'sammy16@gmail.com', '$2y$10$LzToNkmKlZMdH6O0Ngol0uYw807xEvB4flqCE/bjmrVW/u7Diy6TK', 0, 0),
(5, 'Madame', 'Celina', 'Fellows', '2013-09-17', 'katrina_herzog@gmail.com', '$2y$10$LzToNkmKlZMdH6O0Ngol0uYw807xEvB4flqCE/bjmrVW/u7Diy6TK', 0, 0),
(6, 'Monsieur', 'Jesse', 'Owens', '2019-03-19', 'maryam.koss@hotmail.com', '$2y$10$LzToNkmKlZMdH6O0Ngol0uYw807xEvB4flqCE/bjmrVW/u7Diy6TK', 0, 1),
(7, 'Madame', 'Erlinda', 'Gibbins', '1996-12-22', 'sasha91@hotmail.com', '$2y$10$LzToNkmKlZMdH6O0Ngol0uYw807xEvB4flqCE/bjmrVW/u7Diy6TK', 0, 0),
(8, 'Madame', 'Marie', 'Avgeropoulos', '2001-09-30', 'dillan.hackett@yahoo.com', '$2y$10$LzToNkmKlZMdH6O0Ngol0uYw807xEvB4flqCE/bjmrVW/u7Diy6TK', 0, 0),
(9, 'Madame', 'Nastia', 'Liukin', '1997-09-12', 'elwyn_rohan53@yahoo.com', '$2y$10$LzToNkmKlZMdH6O0Ngol0uYw807xEvB4flqCE/bjmrVW/u7Diy6TK', 0, 1),
(10, 'Madame', 'Emelina', 'Dolan', '1994-01-31', 'emerald20@gmail.com', '$2y$10$LzToNkmKlZMdH6O0Ngol0uYw807xEvB4flqCE/bjmrVW/u7Diy6TK', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_association`
--

CREATE TABLE `user_association` (
                                    `id` int(11) NOT NULL,
                                    `label` varchar(50) NOT NULL,
                                    `phone` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_association`
--

INSERT INTO `user_association` (`id`, `label`, `phone`) VALUES
(5, 'SPA', '2558953866'),
(8, 'Arkwild', '7183515099');

-- --------------------------------------------------------

--
-- Table structure for table `user_club`
--

CREATE TABLE `user_club` (
                             `id` int(11) NOT NULL,
                             `label` varchar(50) NOT NULL,
                             `phone` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_club`
--

INSERT INTO `user_club` (`id`, `label`, `phone`) VALUES
(4, 'CSO', '5656985655'),
(6, 'Moto Millau', '7183515099');

-- --------------------------------------------------------

--
-- Table structure for table `user_company`
--

CREATE TABLE `user_company` (
                                `id` int(11) NOT NULL,
                                `label` varchar(50) NOT NULL,
                                `phone` varchar(14) NOT NULL,
                                `siret` varchar(17) NOT NULL,
                                `tva_number` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_company`
--

INSERT INTO `user_company` (`id`, `label`, `phone`, `siret`, `tva_number`) VALUES
(1, 'Google', '0759677844', '442 293 775 00031', 'FR04442293775');

-- --------------------------------------------------------

--
-- Table structure for table `user_registration`
--

CREATE TABLE `user_registration` (
                                     `id` int(11) NOT NULL,
                                     `id_user` int(11) NOT NULL,
                                     `id_event` int(11) NOT NULL,
                                     `is_accepted` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_registration`
--

INSERT INTO `user_registration` (`id`, `id_user`, `id_event`, `is_accepted`) VALUES
(1, 3, 1, 0),
(2, 4, 1, 1),
(3, 5, 1, 1),
(4, 1, 2, 1),
(5, 6, 2, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `difficulte`
--
ALTER TABLE `difficulte`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `evenement`
--
ALTER TABLE `evenement`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Event_Mechanical_Sport` (`id_mechanical_sport`),
  ADD KEY `FK_Event_User` (`id_user`);

--
-- Indexes for table `event_category`
--
ALTER TABLE `event_category`
    ADD PRIMARY KEY (`id_event`,`id_category`),
  ADD KEY `FK_Event_Category_Category` (`id_category`);

--
-- Indexes for table `mechanical_sport`
--
ALTER TABLE `mechanical_sport`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `spot`
--
ALTER TABLE `spot`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Spot_User` (`id_user`),
  ADD KEY `FK_Spot_Difficulty` (`id_difficulty`);

--
-- Indexes for table `spot_mechanical_sport`
--
ALTER TABLE `spot_mechanical_sport`
    ADD PRIMARY KEY (`id_mechanical_sport`,`id_spot`),
  ADD KEY `FK_Spot_Mechanical_Sport_Spot` (`id_spot`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_association`
--
ALTER TABLE `user_association`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_club`
--
ALTER TABLE `user_club`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_company`
--
ALTER TABLE `user_company`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_registration`
--
ALTER TABLE `user_registration`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK_User_Registration_User` (`id_user`),
  ADD KEY `FK_User_Registration_Event` (`id_event`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `difficulte`
--
ALTER TABLE `difficulte`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `evenement`
--
ALTER TABLE `evenement`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `mechanical_sport`
--
ALTER TABLE `mechanical_sport`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `spot`
--
ALTER TABLE `spot`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user_registration`
--
ALTER TABLE `user_registration`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `evenement`
--
ALTER TABLE `evenement`
    ADD CONSTRAINT `FK_Event_Mechanical_Sport` FOREIGN KEY (`id_mechanical_sport`) REFERENCES `mechanical_sport` (`id`),
  ADD CONSTRAINT `FK_Event_User` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `event_category`
--
ALTER TABLE `event_category`
    ADD CONSTRAINT `FK_Event_Category_Category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FK_Event_Category_Event` FOREIGN KEY (`id_event`) REFERENCES `evenement` (`id`);

--
-- Constraints for table `spot`
--
ALTER TABLE `spot`
    ADD CONSTRAINT `FK_Spot_Difficulty` FOREIGN KEY (`id_difficulty`) REFERENCES `difficulte` (`id`),
  ADD CONSTRAINT `FK_Spot_User` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `spot_mechanical_sport`
--
ALTER TABLE `spot_mechanical_sport`
    ADD CONSTRAINT `FK_Spot_Mechanical_Sport_Mechanical_Sport` FOREIGN KEY (`id_mechanical_sport`) REFERENCES `mechanical_sport` (`id`),
  ADD CONSTRAINT `FK_Spot_Mechanical_Sport_Spot` FOREIGN KEY (`id_spot`) REFERENCES `spot` (`id`);

--
-- Constraints for table `user_association`
--
ALTER TABLE `user_association`
    ADD CONSTRAINT `FK_User_Association_User` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `user_club`
--
ALTER TABLE `user_club`
    ADD CONSTRAINT `FK_User_Club_User` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `user_company`
--
ALTER TABLE `user_company`
    ADD CONSTRAINT `FK_User_Company_User` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `user_registration`
--
ALTER TABLE `user_registration`
    ADD CONSTRAINT `FK_User_Registration_Event` FOREIGN KEY (`id_event`) REFERENCES `evenement` (`id`),
  ADD CONSTRAINT `FK_User_Registration_User` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id`);

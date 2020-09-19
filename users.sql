-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Сен 19 2020 г., 22:56
-- Версия сервера: 10.4.13-MariaDB
-- Версия PHP: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `test`
--

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `LOGIN` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `REPASSWORD` varchar(50) NOT NULL,
  `GENDER` varchar(10) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `COMMENT` varchar(100) NOT NULL,
  `AGREE` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`ID`, `LOGIN`, `PASSWORD`, `NAME`, `REPASSWORD`, `GENDER`, `ADDRESS`, `COMMENT`, `AGREE`) VALUES
(1, 'login', 'password', 'Bogdan', '', '', '', '', ''),
(2, 'password1', 'login1', 'Pshenin', '', '', '', '', ''),
(3, 'Doc', 'Paradox71', 'Alex', '', '', '', '', ''),
(4, 'dante@fuib.com', 'Aa111111', 'dante', 'Aa111111', 'male', 'crimea', 'Bla bla bla!', 'on'),
(5, 'Doc', 'Paradox71', 'Alex', 'Paradox71', 'male', 'Kyiv, Khreshyatik 2', 'Bla-bla-bla!', 'on'),
(6, 'Form@fuib.com', 'Aa111111', 'First', 'Aa111111', 'male', 'crimea', 'First reg form', 'on'),
(7, 'a@yandex.ua', 'Aa123456', 'astral', 'Aa123456', 'male', 'lnr', 'start', 'on'),
(8, 'admin@gmail.com', 'Qq1234567', 'MVC', 'Qq1234567', 'male', 'dnr', 'AFK', 'on'),
(9, 'dante@yandex.ua', 'As123456', 'Dante', 'As123456', 'male', 'lnr', 'Date is here', 'on'),
(10, 'hash@yandex.ua', '-5022f4b52d13e8d3a791deaf88f04062', 'Hash', 'Aa123456', 'male', 'lnr', 'Hash password', 'on'),
(11, 'salt@fuib.com', '27b243435a1e8f739696d1f61d88d305', 'Salt', '27b243435a1e8f739696d1f61d88d305', 'male', 'lnr', 'Salt', 'on'),
(12, 'Aa111111@ukr.net', '27b243435a1e8f739696d1f61d88d305', 'Aa111111', '27b243435a1e8f739696d1f61d88d305', 'male', 'lnr', 'Aa111111', 'on'),
(13, 'kjjlkjl', '-20953b2b07f31bd4413c5bfd05e9f69a', 'kjhkjh', '-3fcac2b348399c8cabd87f1ecc6c74c3', 'male', 'lnr', 'gf', 'on'),
(14, 'kjkjhk', '-42362f26290cfc485b2277cc5ecfa8dc', '', '-42362f26290cfc485b2277cc5ecfa8dc', 'male', 'lnr', '', 'on'),
(15, 'spring@gmail.com', '27b243435a1e8f739696d1f61d88d305', 'Aa111111', '27b243435a1e8f739696d1f61d88d305', 'male', 'lnr', 'spring reg', 'on'),
(16, 'spring@gmail.com', '-9b83e22525fb4aa726a3473d0690120', 'Aa111111', '-9b83e22525fb4aa726a3473d0690120', 'male', 'lnr', 'spring reg2', 'on'),
(17, 'final@itea.com', '27b243435a1e8f739696d1f61d88d305', 'Aa111111', '27b243435a1e8f739696d1f61d88d305', 'female', 'crimea', 'spring final reg', 'on');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

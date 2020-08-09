-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Авг 04 2020 г., 20:42
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
(6, 'Form@fuib.com', 'Aa111111', 'First', 'Aa111111', 'male', 'crimea', 'First reg form', 'on');

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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

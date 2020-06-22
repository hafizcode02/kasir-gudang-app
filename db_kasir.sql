-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 07, 2020 at 02:02 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kasir`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_barang`
--

CREATE TABLE `tbl_barang` (
  `kd_barang` varchar(15) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `kategori` varchar(15) NOT NULL,
  `harga_jual` int(90) NOT NULL,
  `harga_beli` int(90) NOT NULL,
  `stok` int(30) NOT NULL,
  `NAMA_USER` varchar(30) NOT NULL,
  `ID_USER` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_barang`
--

INSERT INTO `tbl_barang` (`kd_barang`, `nama_barang`, `kategori`, `harga_jual`, `harga_beli`, `stok`, `NAMA_USER`, `ID_USER`) VALUES
('AT0001', 'Pulpen Standar', 'Alat Tulis', 2000, 1000, 100, 'Hago', 'USER0002'),
('AT0002', 'Pensil', 'Alat Tulis', 2500, 1500, 100, 'Hago', 'USER0002'),
('AT0003', 'Kuas', 'Alat Tulis', 5000, 4000, 100, 'Hago', 'USER0002'),
('MI0001', 'Aqua', 'Minuman', 4000, 3000, 100, 'Hago', 'USER0002'),
('MI0002', 'Mizone', 'Minuman', 5000, 3000, 100, 'Hago', 'USER0002'),
('MI0003', 'Torpedo', 'Minuman', 1500, 1000, 100, 'Hago', 'USER0002'),
('MK0001', 'Indomie', 'Makanan', 3000, 2000, 100, 'Hago', 'USER0002'),
('MK0002', 'Kerupuk', 'Makanan', 5000, 3000, 100, 'Hago', 'USER0002'),
('MK0003', 'Telor', 'Makanan', 1500, 1000, 100, 'Hago', 'USER0002'),
('PK0001', 'Baju Ultramen', 'Pakaian', 100000, 80000, 100, 'Hago', 'USER0002'),
('PK0002', 'Baju Muslim', 'Pakaian', 80000, 60000, 100, 'Hago', 'USER0002'),
('PK0003', 'Jaket', 'Pakaian', 100000, 80000, 50, 'Hago', 'USER0002');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_detailtransaksi`
--

CREATE TABLE `tbl_detailtransaksi` (
  `id_transaksi` bigint(10) NOT NULL,
  `kd_barang` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_detailtransaksi`
--

INSERT INTO `tbl_detailtransaksi` (`id_transaksi`, `kd_barang`) VALUES
(1, 'MI0003'),
(2, 'MI0003'),
(3, 'MK0002'),
(4, 'MK0001'),
(5, 'MK0002'),
(6, 'MK0002'),
(7, 'MK0003'),
(8, 'MK0002'),
(9, 'MK0001'),
(10, 'MK0001'),
(11, 'MK0002'),
(12, 'MK0001'),
(13, 'MK0002'),
(14, 'MK0001'),
(15, 'MK0003'),
(16, 'MK0001'),
(17, 'MK0003'),
(18, 'MK0002'),
(19, 'MK0002'),
(20, 'AT0002'),
(21, 'MK0001'),
(22, 'MK0002'),
(23, 'AT0001'),
(24, 'MK0001'),
(25, 'MK0002'),
(26, 'MK0001'),
(27, 'MK0001'),
(28, 'MK0001'),
(29, 'MK0002'),
(30, 'MK0001'),
(31, 'MK0002'),
(32, 'MK0003'),
(33, 'MK0001'),
(34, 'MK0001'),
(35, 'MK0002'),
(36, 'MK0002'),
(37, 'MK0003'),
(38, 'MK0001'),
(39, 'MK0002'),
(40, 'MK0003'),
(41, 'MK0002'),
(42, 'MK0003'),
(43, 'MK0002'),
(44, 'MK0002'),
(45, 'MK0003'),
(46, 'MK0002'),
(47, 'MK0002'),
(48, 'PK0001'),
(49, 'MK0001'),
(50, 'MK0002'),
(51, 'MK0003'),
(52, 'MK0002'),
(53, 'MI0001'),
(54, 'MK0002'),
(55, 'MK0001'),
(56, 'MK0002'),
(57, 'MI0003'),
(58, 'PK0001'),
(59, 'MK0001'),
(60, 'MK0003'),
(61, 'PK0001'),
(62, 'MK0002'),
(63, 'MI0003'),
(64, 'AT0003'),
(65, 'MK0001'),
(66, 'MK0002'),
(67, 'MK0002'),
(68, 'MK0003'),
(69, 'MK0002'),
(70, 'PK0001'),
(71, 'MK0002'),
(72, 'AT0003'),
(73, 'MK0001'),
(74, 'MK0002'),
(75, 'MK0002'),
(76, 'PK0001'),
(77, 'MK0003'),
(78, 'MI0001'),
(79, 'MK0001'),
(80, 'MK0002'),
(81, 'MK0003'),
(82, 'MK0002'),
(83, 'MK0001'),
(84, 'MK0002'),
(85, 'MK0002'),
(86, 'MK0002'),
(87, 'MK0001'),
(88, 'MK0001'),
(89, 'MK0002'),
(90, 'MK0003'),
(91, 'MK0002'),
(92, 'MK0002'),
(93, 'MK0002'),
(94, 'MK0002'),
(95, 'MK0001'),
(96, 'MK0002'),
(97, 'MK0003'),
(98, 'MK0001'),
(99, 'MK0002'),
(100, 'MK0002'),
(101, 'AT0001'),
(102, 'MK0001'),
(103, 'MK0002'),
(104, 'MK0003'),
(105, 'PK0001'),
(106, 'MK0002'),
(107, 'MK0002'),
(108, 'MK0003'),
(109, 'MK0002'),
(110, 'MK0002'),
(111, 'MK0002'),
(112, 'MK0003'),
(113, 'at0001'),
(114, 'AT0001'),
(115, 'AT0002');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_level`
--

CREATE TABLE `tbl_level` (
  `ID_LEVEL` int(2) NOT NULL,
  `LEVEL` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_level`
--

INSERT INTO `tbl_level` (`ID_LEVEL`, `LEVEL`) VALUES
(1, 'ADMIN'),
(2, 'GUDANG'),
(3, 'KASIR');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_penjualan`
--

CREATE TABLE `tbl_penjualan` (
  `kd_barang` varchar(15) NOT NULL,
  `nota` varchar(15) NOT NULL,
  `ID_USER` varchar(10) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `harga_jual` int(12) NOT NULL,
  `tgl_penjualan` varchar(20) NOT NULL,
  `jml_barang` int(10) NOT NULL,
  `ttl_bayar` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_suplier`
--

CREATE TABLE `tbl_suplier` (
  `id_suplier` varchar(15) NOT NULL,
  `kd_pembelian` varchar(15) NOT NULL,
  `nama_suplier` varchar(30) NOT NULL,
  `nope_suplier` varchar(15) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaksi`
--

CREATE TABLE `tbl_transaksi` (
  `id_transaksi` bigint(10) NOT NULL,
  `kode_transaksi` int(11) NOT NULL,
  `nama_kasir` varchar(30) NOT NULL,
  `tanggal` datetime NOT NULL,
  `harga_total` int(10) NOT NULL,
  `jumlah_barang` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_transaksi`
--

INSERT INTO `tbl_transaksi` (`id_transaksi`, `kode_transaksi`, `nama_kasir`, `tanggal`, `harga_total`, `jumlah_barang`) VALUES
(1, 1, 'Kasir', '2019-03-05 17:56:20', 2000, 2),
(2, 1, 'Kasir', '2019-03-05 17:57:10', 3000, 2),
(3, 2, 'Kasir', '2019-03-06 08:38:56', 50000, 10),
(4, 3, 'Kasir', '2019-03-06 08:40:30', 30000, 10),
(5, 3, 'Kasir', '2019-03-06 08:40:45', 50000, 10),
(6, 4, 'Kasir', '2019-03-06 08:42:58', 50000, 10),
(7, 4, 'Kasir', '2019-03-06 08:43:10', 4500, 3),
(8, 5, 'Kasir', '2019-03-06 09:28:33', 50000, 10),
(9, 6, '', '2019-03-06 09:30:55', 30000, 10),
(10, 7, 'Kasir', '2019-03-06 09:36:23', 30000, 10),
(11, 7, 'Kasir', '2019-03-06 09:36:31', 50000, 10),
(12, 8, 'Kasir', '2019-03-06 20:56:25', 0, 5),
(13, 8, 'Kasir', '2019-03-06 20:57:05', 15000, 10),
(14, 9, '', '2019-03-06 20:59:31', 30000, 10),
(15, 9, '', '2019-03-06 20:59:40', 15000, 10),
(16, 10, '', '2019-03-06 21:00:12', 30000, 10),
(17, 10, '', '2019-03-06 21:00:21', 15000, 10),
(18, 11, '', '2019-03-06 21:00:59', 0, 10),
(19, 11, '', '2019-03-06 21:01:13', 50000, 10),
(20, 11, '', '2019-03-06 21:01:23', 100000, 10),
(21, 12, 'Kasir', '2019-03-07 11:44:06', 30000, 10),
(22, 12, 'Kasir', '2019-03-07 11:44:23', 50000, 10),
(23, 13, '', '2019-03-07 11:45:03', 20000, 10),
(24, 14, 'Kasir', '2019-03-07 11:47:57', 30000, 10),
(25, 14, 'Kasir', '2019-03-07 11:48:05', 50000, 10),
(26, 15, '', '2019-03-07 11:49:10', 30000, 10),
(27, 16, '', '2019-03-07 11:50:15', 30000, 10),
(28, 17, '', '2019-03-07 11:50:38', 30000, 10),
(29, 17, '', '2019-03-07 11:50:47', 50000, 10),
(30, 18, '', '2019-03-07 11:53:30', 30000, 10),
(31, 18, '', '2019-03-07 11:53:41', 50000, 10),
(32, 18, '', '2019-03-07 11:53:48', 15000, 10),
(33, 18, '', '2019-03-07 11:54:24', 30000, 10),
(34, 19, '', '2019-03-07 11:57:15', 30000, 10),
(35, 19, '', '2019-03-07 11:57:25', 50000, 10),
(36, 20, '', '2019-03-07 11:57:45', 50000, 10),
(37, 20, '', '2019-03-07 11:57:52', 15000, 10),
(38, 21, '', '2019-03-07 12:02:24', 30000, 10),
(39, 21, '', '2019-03-07 12:02:34', 50000, 10),
(40, 21, '', '2019-03-07 12:02:48', 15000, 10),
(41, 22, '', '2019-03-07 12:05:31', 50000, 10),
(42, 22, '', '2019-03-07 12:05:43', 15000, 10),
(43, 23, '', '2019-03-07 12:08:30', 50000, 10),
(44, 24, '', '2019-03-07 12:12:09', 50000, 10),
(45, 24, '', '2019-03-07 12:12:18', 15000, 10),
(46, 24, '', '2019-03-07 12:12:28', 75000, 15),
(47, 25, '', '2019-03-07 12:13:56', 50000, 10),
(48, 25, '', '2019-03-07 12:14:05', 1000000, 10),
(49, 26, '', '2019-03-07 12:19:21', 30000, 10),
(50, 27, '', '2019-03-07 12:22:09', 50000, 10),
(51, 28, '', '2019-03-07 12:22:53', 3000, 2),
(52, 29, 'Kasir', '2019-03-07 19:10:13', 50000, 10),
(53, 29, 'Kasir', '2019-03-07 19:10:24', 12000, 3),
(54, 30, '', '2019-03-07 19:11:42', 50000, 10),
(55, 31, 'Kasir', '2019-03-07 19:13:32', 30000, 10),
(56, 32, 'Kasir', '2019-03-07 19:14:46', 50000, 10),
(57, 32, 'Kasir', '2019-03-07 19:14:56', 15000, 10),
(58, 32, 'Kasir', '2019-03-07 19:15:05', 1000000, 10),
(59, 33, 'Kasir', '2019-03-07 19:17:15', 30000, 10),
(60, 33, 'Kasir', '2019-03-07 19:17:23', 15000, 10),
(61, 34, 'Kasir', '2019-03-07 19:19:47', 200000, 2),
(62, 34, 'Kasir', '2019-03-07 19:19:54', 50000, 10),
(63, 34, 'Kasir', '2019-03-07 19:20:06', 15000, 10),
(64, 34, 'Kasir', '2019-03-07 19:20:19', 10000, 2),
(65, 35, 'Kasir', '2019-03-07 19:21:58', 30000, 10),
(66, 35, 'Kasir', '2019-03-07 19:22:04', 50000, 10),
(67, 36, '', '2019-03-07 19:24:19', 50000, 10),
(68, 36, '', '2019-03-07 19:24:28', 15000, 10),
(69, 37, '', '2019-03-07 19:26:28', 50000, 10),
(70, 37, '', '2019-03-07 19:26:35', 100000, 1),
(71, 38, '', '2019-03-07 19:30:35', 50000, 10),
(72, 38, '', '2019-03-07 19:30:45', 25000, 5),
(73, 39, '', '2019-03-07 19:31:40', 30000, 10),
(74, 39, '', '2019-03-07 19:31:47', 25000, 5),
(75, 40, 'Kasir', '2019-03-07 19:47:43', 50000, 10),
(76, 40, 'Kasir', '2019-03-07 19:47:50', 100000, 1),
(77, 41, '', '2019-03-07 19:49:41', 15000, 10),
(78, 41, '', '2019-03-07 19:49:49', 8000, 2),
(79, 42, '', '2019-03-07 19:50:42', 30000, 10),
(80, 42, '', '2019-03-07 19:50:50', 5000, 1),
(81, 43, '', '2019-03-07 19:51:58', 15000, 10),
(82, 43, '', '2019-03-07 19:52:04', 10000, 2),
(83, 44, '', '2019-03-07 19:54:10', 30000, 10),
(84, 44, '', '2019-03-07 19:54:20', 50000, 10),
(85, 45, '', '2019-03-07 19:54:51', 5000, 1),
(86, 46, '', '2019-03-07 19:57:34', 50000, 10),
(87, 46, '', '2019-03-07 19:57:41', 6000, 2),
(88, 47, '', '2019-03-07 19:58:32', 30000, 10),
(89, 48, 'Kasir', '2019-03-07 20:00:47', 10000, 2),
(90, 48, 'Kasir', '2019-03-07 20:01:04', 9000, 6),
(91, 49, '', '2019-03-07 20:03:22', 5000, 1),
(92, 50, '', '2019-03-07 20:04:05', 50000, 10),
(93, 51, '', '2019-03-07 20:04:40', 50000, 10),
(94, 52, '', '2019-03-07 20:06:43', 5000, 1),
(95, 53, 'Kasir', '2019-03-13 10:28:10', 30000, 10),
(96, 53, 'Kasir', '2019-03-13 10:28:19', 50000, 10),
(97, 53, 'Kasir', '2019-03-13 10:28:26', 15000, 10),
(98, 54, 'Kasir', '2019-03-17 17:01:52', 15000, 5),
(99, 54, 'Kasir', '2019-03-17 17:02:03', 15000, 3),
(100, 55, '', '2019-03-17 17:04:08', 20000, 4),
(101, 55, '', '2019-03-17 17:04:16', 4000, 2),
(102, 56, 'Kasir', '2019-03-17 17:10:37', 30000, 10),
(103, 56, 'Kasir', '2019-03-17 17:10:47', 50000, 10),
(104, 56, 'Kasir', '2019-03-17 17:10:59', 3000, 2),
(105, 57, '', '2019-03-17 17:12:18', 100000, 1),
(106, 58, '', '2019-03-17 17:14:26', 5000, 1),
(107, 59, '', '2019-03-17 17:14:43', 15000, 3),
(108, 60, '', '2019-03-17 17:15:17', 1500, 1),
(109, 60, '', '2019-03-17 17:15:28', 10000, 2),
(110, 61, '', '2019-03-17 17:15:53', 50000, 10),
(111, 61, '', '2019-03-17 17:16:05', 50000, 10),
(112, 62, '', '2019-03-17 17:19:32', 3000, 2),
(113, 63, 'Kasir', '2019-03-20 07:22:45', 24000, 12),
(114, 64, 'Kasir', '2019-08-15 19:03:30', 4000, 2),
(115, 64, 'Kasir', '2019-08-15 19:03:39', 5000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `ID_USER` varchar(10) NOT NULL,
  `ID_LEVEL` int(2) NOT NULL,
  `NAMA_USER` varchar(30) DEFAULT NULL,
  `JK` char(1) DEFAULT NULL,
  `TANGGAL_LAHIR` varchar(50) NOT NULL,
  `NOPE` varchar(13) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`ID_USER`, `ID_LEVEL`, `NAMA_USER`, `JK`, `TANGGAL_LAHIR`, `NOPE`, `USERNAME`, `PASSWORD`) VALUES
('USER0001', 1, 'Hafiz', 'L', '2002-12-31', '085321349681', 'hafiz', 'hafiz'),
('USER0002', 2, 'Hago', 'L', '2018-12-24', '09876543233', 'hago', 'hago'),
('USER0003', 3, 'Kasir', 'P', '2012-12-07', '07896457335', 'lusapa', 'lusapa'),
('USER0004', 1, 'GAGA', 'L', '2019-03-01', '098765', 'dsda', 'sdsa'),
('USER0005', 1, 'asdasd', 'L', '2019-03-01', '09564', 'dsfad', 'dasda'),
('USER0006', 2, 'OALAH', 'L', '', '87656787', 'oalah', 'oalah\r'),
('USER0007', 1, 'hahah', 'L', '', '9876545678', 'hahah', 'hahah\r');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD PRIMARY KEY (`kd_barang`),
  ADD KEY `ID_USER` (`ID_USER`);

--
-- Indexes for table `tbl_detailtransaksi`
--
ALTER TABLE `tbl_detailtransaksi`
  ADD KEY `id_transaksi` (`id_transaksi`,`kd_barang`),
  ADD KEY `kd_barang` (`kd_barang`);

--
-- Indexes for table `tbl_level`
--
ALTER TABLE `tbl_level`
  ADD PRIMARY KEY (`ID_LEVEL`);

--
-- Indexes for table `tbl_penjualan`
--
ALTER TABLE `tbl_penjualan`
  ADD PRIMARY KEY (`nota`),
  ADD KEY `kd_barang` (`kd_barang`),
  ADD KEY `ID_USER` (`ID_USER`);

--
-- Indexes for table `tbl_suplier`
--
ALTER TABLE `tbl_suplier`
  ADD PRIMARY KEY (`id_suplier`);

--
-- Indexes for table `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`ID_USER`) USING BTREE,
  ADD KEY `ID_LEVEL` (`ID_LEVEL`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD CONSTRAINT `tbl_barang_ibfk_1` FOREIGN KEY (`ID_USER`) REFERENCES `tbl_user` (`ID_USER`);

--
-- Constraints for table `tbl_detailtransaksi`
--
ALTER TABLE `tbl_detailtransaksi`
  ADD CONSTRAINT `tbl_detailtransaksi_ibfk_1` FOREIGN KEY (`kd_barang`) REFERENCES `tbl_barang` (`kd_barang`),
  ADD CONSTRAINT `tbl_detailtransaksi_ibfk_2` FOREIGN KEY (`id_transaksi`) REFERENCES `tbl_transaksi` (`id_transaksi`);

--
-- Constraints for table `tbl_penjualan`
--
ALTER TABLE `tbl_penjualan`
  ADD CONSTRAINT `tbl_penjualan_ibfk_2` FOREIGN KEY (`kd_barang`) REFERENCES `tbl_barang` (`kd_barang`),
  ADD CONSTRAINT `tbl_penjualan_ibfk_3` FOREIGN KEY (`ID_USER`) REFERENCES `tbl_user` (`ID_USER`);

--
-- Constraints for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`ID_LEVEL`) REFERENCES `tbl_level` (`ID_LEVEL`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

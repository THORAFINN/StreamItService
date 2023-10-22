# SUPPORT RELEASE NOTES
  
  ...for each commit, we add version and releted chnages what features and database chages  
  
--------------------------------------------------------------------------------------------------------------------------------------------------

### Version 1.0.0 ###
  
  * Enhancement 
      - Added Support Release notes for maintaining versions of next subsequent enhancement.
   
  - Database Changes
	
	 create database streamItMain;
	 use streamItMain;
	 
	 CREATE TABLE `login` (
	  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	  `name` varchar(256) NOT NULL,
	  `username` varchar(100) NOT NULL,
	  `password` varchar(30) NOT NULL,
	  `crdt_dt` datetime NOT NULL DEFAULT now(),
	  `mod_dt` datetime DEFAULT NUL) ;
	  
    CREATE TABLE `src_hdr` (
	  `sr_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	  `sr_name` varchar(64) NOT NULL,
	  `sr_active` int NOT NULL,
	  `sr_config` json DEFAULT NULL,
	  `sr_moddt` datetime DEFAULT now(),
	  `sr_appid` varchar(64) NOT NULL,
	  `sr_key` varchar(64) NOT NULL);
	  
    insert into src_hdr (sr_name, sr_active, sr_config, sr_moddt) values ('StreamItUI', 1,'[{"valid_apis": [{"key": "1", "value": "true"}, {"key": "2", "value": "true"}, {"key":"3","value":"true"}]}]',now());
    
    CREATE TABLE `thumbnail` (
	  `thn_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	  `thn_name` varchar(50) NOT NULL,
	  `thn_filepath` varchar(250) NOT NULL,
	  `thn_filename` varchar(150) NOT NULL,
	  `thn_ref` varchar(100) NOT NULL,
	  `thn_crd_dt` datetime NOT NULL DEFAULT now()) ;	  
	  
	
	CREATE TABLE `video` (
	  `v_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	  `user_id` int NOT NULL REFERENCES `login` (`id`),
	  `thn_id` int NOT NULL REFERENCES `thumbnail` (`thn_id`),
	  `v_name` varchar(256) NOT NULL,
	  `v_file_name` varchar(256) NOT NULL,
	  `v_file_path` varchar(506) NOT NULL,
	  `v_file_size` mediumtext NOT NULL,
	  `v_ref` varchar(128) NOT NULL UNIQUE,
	  `v_crd_dt` datetime NOT NULL DEFAULT now()) ;
	  


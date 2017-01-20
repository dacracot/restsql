BEGIN TRANSACTION;
/*------------------------------------------------------------------------*/
INSERT INTO `usr` VALUES
	(1,'Craig','cothren2@llnl.gov');
/*-------------------------------------*/
INSERT INTO `src` VALUES
	(1,'Cables','/opt/xipi/solr/cables',2),
	(2,'Twitter','/opt/xipi/solr/twitter',2),
	(3,'Phone','/opt/xipi/solr/phone',2),
	(4,'WV3','/opt/xipi/nada/wv3',1),
	(5,'NITF','/opt/xipi/nada/nitf',1),
	(6,'JPEG2K','/opt/xipi/nada/jpg2k',1),
	(7,'WV3','/opt/xipi/cad/wv3',3),
	(8,'NITF','/opt/xipi/cad/nitf',3),
	(9,'JPEG2K','/opt/xipi/cad/jpg2k',3);
/*-------------------------------------*/
INSERT INTO `dest` VALUES
	(1,'WSIC'),
	(2,'Spectrum'),
	(3,'WISC w/CAD');
/*------------------------------------------------------------------------*/
COMMIT;

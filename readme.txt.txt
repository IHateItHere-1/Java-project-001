AbstractPTID -> abstract class defining a template for PTID functions
CustomFile -> wrapps XML / L3D files
DB -> deals with the Db
drivercode -> acts as start point 
FileFinder -> deals with finding xml/L3Ds
L3DDataType -> encaps an L3D file
RelationBuilder -> builds the relations between L3D and XML
Relations -> holds data about realtions between L3D and XML
Soprting -> sorts data of the custom file type or any class that implements AbstractPTID
XML -> deals with reading XML data
XMLDataType -> wraps XML file
XMLDBDATA -> holds data from XML




the effective outline of this project is we have a .xml file that contains a PT ID, time and date of exam and demographics of the PT.

 
we have the images which are L3Ds (3D ultrasound file type)
these are named as PTID_DATE_TIME.l3d

 
the program will take in multiple groups of xmls / l3ds
it will generate a relationality between the xmls and associated L3Ds for each study.
it will move the L3Ds into a logic file path YEAR/MONTH/DAY/L3DFILE
so that manual searching with knowledge of the exam date and PTID would very simple should the program experience an issue. (by traversing the directory tree in file explorer)

this program should be able to be searched by PTID / date of exam / time / date
and return details of images for any study that matches the search criteria. 

the algorithm section will take the form of relating the xmls  and L3Ds as efficiently as possible.



if more time -> Manage time better -> add the GUI 

optional -> debug output for relations made | fast sorting algo for building relations | R/W as little as possible to DB file 
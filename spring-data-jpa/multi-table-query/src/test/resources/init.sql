TRUNCATE company;
TRUNCATE person;
TRUNCATE school;

INSERT INTO company
(id,company_name,description)
VALUES
(1,'大华科技有限公司','安防公司'),
(2,'海康威视有限公司','安防公司'),
(3,'宇视有限公司','安防公司'),
(4,'阿里巴巴有限公司','互联网公司'),
(5,'网易有限公司','互联网公司');

INSERT INTO school
(id,name,description)
VALUES
(1,'杭州电子科技大学',''),
(2,'浙江工商大学','');

INSERT INTO person
(id,age,gender,home_address,name,company_id,school_id)
VALUES
(1,12,'MALE','A','小蓝',1,1),
(2,12,'FEMALE','B','小灰',3,2),
(3,16,'MALE','C','小白',4,2),
(4,28,'FEMALE','D','小黑',2,1),
(5,34,'FEMALE','E','小绿',2,1),
(6,55,'MALE','F','小黄',1,1),
(7,21,'FEMALE','G','小红',2,2),
(8,15,'FEMALE','H','小明',4,2);
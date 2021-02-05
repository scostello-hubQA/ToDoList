INSERT INTO TO_DO_DOMAIN 
(NAME) 
VALUES
('THIS IS A PRACTICE TO DO LIST NAME'),
('Cleaning List');




INSERT INTO TASK_DOMAIN 
(ACT, PRIORITY, DATE, NOTES, COMPLETED, TO_DO_ID)
VALUES
('Practice',3, '22/06/2022','this is a test to make sure we can prepopulate the database', false, 1),
('Hoover',4, '21/06/2022','need to hoover all of downstairs', false, 2),
('Dishes',3, '21/06/2022','fill the dishwasher', false, 2);
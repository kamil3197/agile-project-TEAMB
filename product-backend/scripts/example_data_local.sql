-- US001: view job roles
insert into JobRoles(job_role_id, job_role_title) values
    (1,'Engineering'),
    (2,'Engineering, Strategy and Planning'),
    (3,'Architecture'),
    (4,'Testing and Quality Assurance'),
    (5,'Product Specialist');

-- US024-register-system & US040-login-system
insert into `User`(email, password, role) values
    ('test1@kainos@com', '$2a$12$oMfM6nZMcIkUSSO8DeL/.OXBLetvOV4p/4zan2kxe3BtnEAmLINUm', 'Admin'),
    ('test2@kainos@com', '$2a$12$h0/SQJD1D2BbaMguYAoABeHx3rTaKCp6.XAy1oLwcOynIxhPfAhGa', 'Employee');
    -- pw1: Test1234!
    -- pw2: Test12345!

-- Insert data into Specifications table
INSERT INTO Specifications (role_id, summary,sharepoint_link) VALUES
    (1, 'you will be responsible for developing high quality solutions which delight our customers and impact the lives of users worldwide', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Apprentice%20Software%20Engineer%20%28Apprentice%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1'),
    (2, 'As a Principal Data Solution Architect in Kainos, youll be accountable for successful delivery of contemporary data solutions across multiple customers.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technology%20Leader%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1'),
    (3, 'As a Principal Data Solution Architect in Kainos, youll be accountable for successful delivery of contemporary data solutions across multiple customers.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1'),
    (4, 'youll work within a multi-skilled agile team, developing and executing functional automated, manual, and non-functional tests to help the team deliver working application software that meets user needs', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FTesting%2FJob%20profile%20%2D%20Test%20Architect%20%28Manager%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FTesting&p=true&ga=1'),
    (5, 'Detailed description for Product Specialist.', 'https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Product%20Specialist%20%28Associate%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1');


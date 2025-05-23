INSERT INTO company (company_id, company_name, company_image, address, description)
VALUES (1, 'MusicSmile Foundation', 'https://storage.googleapis.com/fandora-bucket/company1.jpg', '110-2, Gongneung-dong, Nowon-gu, Seoul',
        'A non-profit organization established for youth who have difficulty accessing culture and arts due to financial difficulties. It operates a music listening room using donated idol albums and goods, and supports cultural exchanges with youth around the world through K-POP.'),
       (2, 'Idol Dream Center', 'https://storage.googleapis.com/fandora-bucket/company2.jpg', '270-4, Gongneung-dong, Nowon-gu, Seoul',
        'Welfare facilities for children and adolescents with developmental disabilities who love music and idol culture. It operates a K-pop experience space using donated albums and photo cards, and provides programs to help improve emotional communication and social skills through music.'),
       (3, 'MusicBridge', 'https://storage.googleapis.com/fandora-bucket/company3.jpg', '758-1, Gongneung-dong, Nowon-gu, Seoul',
        'A social enterprise that supports underprivileged communities through music. Donated idol albums and goods are distributed to youth centers and libraries or used in fundraising auctions for music education programs.'),
       (4, 'SoulHarmony', 'https://storage.googleapis.com/fandora-bucket/company4.jpg', '51-gil, Hwarang-ro, Nowon-gu, Seoul, 17',
        'A nonprofit organization that provides psychological and emotional support for youth through music and culture. Donated idol albums are used in cultural programs or resold to fund mentoring and counseling services.'),
       (5, 'Green Eco Store', 'https://storage.googleapis.com/fandora-bucket/company5.jpg', '29-39, Gongneung 2-dong, Nowon-gu, Seoul',
        'Green Eco Store is a social enterprise recycling store that promotes eco-friendly culture. They collect K-POP albums, merchandise, and books, then resell them or donate them to local children''s centers and underprivileged youth. All proceeds support environmental protection activities.'),
       (6, 'Grow Care Center', 'https://storage.googleapis.com/fandora-bucket/company6.jpg', '107-115, Gongneung 2-dong, Nowon-gu, Seoul',
        'Grow Care Center is a social welfare facility dedicated to supporting children and youth in need. They provide various programs to help children and adolescents grow up healthy and independent. Donated K-POP albums are gifted to the children or made available in community spaces.');

INSERT INTO review (review_id, company_id, review_image, content, created_at)
VALUES (1, 1, 'https://storage.googleapis.com/fandora-bucket/review1.jpg', 'I''m so happy to receive this album! Thank You.', NOW()),
       (2, 1, 'https://storage.googleapis.com/fandora-bucket/review2.jpg', 'I never thought I''d own this album. Thank you.', NOW()),
       (3, 1, 'https://storage.googleapis.com/fandora-bucket/review3.jpg', 'This means a lot to me! I truly appreciate it.', NOW()),
       (4, 1, 'https://storage.googleapis.com/fandora-bucket/review4.jpg', 'I love this team so much! Thank you for the album.', NOW()),
       (5, 2, 'https://storage.googleapis.com/fandora-bucket/review5.jpg', 'This gift made my whole week brighter.', NOW()),
       (6, 2, 'https://storage.googleapis.com/fandora-bucket/review6.jpg', 'I''m smiling so hard right now, thank you!', NOW()),
       (7, 2, 'https://storage.googleapis.com/fandora-bucket/review7.jpg', 'It feels like a dream to hold this album.', NOW()),
       (8, 3, 'https://storage.googleapis.com/fandora-bucket/review8.jpg', 'I can''t believe someone donated this. So grateful!', NOW()),
       (9, 3, 'https://storage.googleapis.com/fandora-bucket/review9.jpg', 'You made me feel seen as a fan.', NOW()),
       (10, 3, 'https://storage.googleapis.com/fandora-bucket/review10.jpg', 'I''ve always wanted this. Thank you from my heart!', NOW()),
       (11, 4, 'https://storage.googleapis.com/fandora-bucket/review11.jpg', 'This album brings me so much comfort.', NOW()),
       (12, 4, 'https://storage.googleapis.com/fandora-bucket/review12.jpg', 'You gave me more than just music. You gave me hope.', NOW()),
       (13, 4, 'https://storage.googleapis.com/fandora-bucket/review13.jpg', 'This album means the world to me. Thank you so much!', NOW()),
       (14, 5, 'https://storage.googleapis.com/fandora-bucket/review14.jpg', 'I love this team so much! Thank you for the album.', NOW()),
       (15, 5, 'https://storage.googleapis.com/fandora-bucket/review15.jpg', 'I''ve always wanted this. Thank you from my heart!', NOW()),
       (16, 6, 'https://storage.googleapis.com/fandora-bucket/review16.jpg', 'This album means the world to me. Thank you so much!', NOW()),
       (17, 6, 'https://storage.googleapis.com/fandora-bucket/review17.jpg', 'It feels like a dream to hold this album.', NOW());
INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("2b7d7475-44b6-4dc0-b3ca-cdfb96b3dda0", "-",""))),'autoescuela', '1234', 'Ismael', 'Kale', 'TEACHER');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("5c718d72-7fc4-4da0-a99e-dfabb1e89cef", "-",""))),'nimitz', '1234', 'Jake', 'Petrulla', 'TEACHER');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("a8484815-809a-4abb-839a-72f2c78aaa51", "-",""))),'dot_net', '1234', 'NA', 'NA', 'TEACHER');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("16254c4d-bb05-4668-a715-96fef62e416f", "-",""))),'android', '1234', 'NA', 'NA', 'TEACHER');

INSERT INTO `itinerary` (`id`, `name`, `teacher`) VALUES ((UNHEX(REPLACE("b15345c1-7697-453e-abdb-41a470bc3566", "-",""))), 'FRONTEND', (UNHEX(REPLACE("2b7d7475-44b6-4dc0-b3ca-cdfb96b3dda0", "-",""))));

INSERT INTO `itinerary` (`id`, `name`, `teacher`) VALUES ((UNHEX(REPLACE("5b077002-e07a-4731-86f7-0f85936df341", "-",""))), 'JAVA', (UNHEX(REPLACE("5c718d72-7fc4-4da0-a99e-dfabb1e89cef", "-",""))));

INSERT INTO `itinerary` (`id`, `name`, `teacher`) VALUES ((UNHEX(REPLACE("36dc8914-b5e9-459c-b1e2-f106fd4ed05b", "-",""))), 'DOT_NET', (UNHEX(REPLACE("a8484815-809a-4abb-839a-72f2c78aaa51", "-",""))));

INSERT INTO `itinerary` (`id`, `name`, `teacher`) VALUES ((UNHEX(REPLACE("99944ab1-c43f-4cb3-a718-c2d23a08cb7a", "-",""))), 'ANDROID', (UNHEX(REPLACE("16254c4d-bb05-4668-a715-96fef62e416f", "-",""))));

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))),0, '1234', 'Joan', 'Garcia', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), 'mail', 'M', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), '2019-06-01');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), '2019-06-02');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), '2019-06-03');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), '2019-06-04');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), '2019-06-05');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), '2019-06-06');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), '2019-06-07');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), '2019-06-08');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), '2019-06-09');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), '2019-06-10');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("6a4b1a9c-5dac-4db3-ac72-d415be594b7c", "-",""))),1, '1234', 'Maria', 'Pons', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("6a4b1a9c-5dac-4db3-ac72-d415be594b7c", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("6a4b1a9c-5dac-4db3-ac72-d415be594b7c", "-",""))), '2019-06-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("6a4b1a9c-5dac-4db3-ac72-d415be594b7c", "-",""))), '2019-06-12');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))),2, '1234', 'Jose', 'López', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), 'mail', 'M', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-13');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-14');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-15');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-16');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-17');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-18');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-19');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-20');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-21');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-22');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("3d7b75fe-50a4-4e1a-a0b2-efacad4c16d1", "-",""))), '2019-06-23');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))),3, '1234', 'Laura', 'Martí', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), '2019-06-24');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), '2019-06-25');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), '2019-06-26');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), '2019-06-27');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), '2019-06-28');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), '2019-06-29');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), '2019-06-30');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), '2019-07-01');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), '2019-07-02');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19235f17-4c4c-455f-a8c8-133f11a345c4", "-",""))), '2019-07-03');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("e9ef5b0f-071c-42d8-9e6e-fc4c0b83d2d6", "-",""))),4, '1234', 'Toni', 'Perez', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("e9ef5b0f-071c-42d8-9e6e-fc4c0b83d2d6", "-",""))), 'mail', 'M', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e9ef5b0f-071c-42d8-9e6e-fc4c0b83d2d6", "-",""))), '2019-07-04');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e9ef5b0f-071c-42d8-9e6e-fc4c0b83d2d6", "-",""))), '2019-07-05');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e9ef5b0f-071c-42d8-9e6e-fc4c0b83d2d6", "-",""))), '2019-07-06');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("f4f396aa-90db-45dc-a318-58c8d66b13cd", "-",""))),5, '1234', 'Aurora', 'Smith', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("f4f396aa-90db-45dc-a318-58c8d66b13cd", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("f4f396aa-90db-45dc-a318-58c8d66b13cd", "-",""))), '2019-07-07');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("f4f396aa-90db-45dc-a318-58c8d66b13cd", "-",""))), '2019-07-08');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("f4f396aa-90db-45dc-a318-58c8d66b13cd", "-",""))), '2019-07-09');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("f4f396aa-90db-45dc-a318-58c8d66b13cd", "-",""))), '2019-07-10');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("f4f396aa-90db-45dc-a318-58c8d66b13cd", "-",""))), '2019-07-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("f4f396aa-90db-45dc-a318-58c8d66b13cd", "-",""))), '2019-07-12');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))),6, '1234', 'Jessica', 'Rosselló', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), '2019-07-13');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), '2019-07-14');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), '2019-07-15');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), '2019-07-16');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), '2019-07-17');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), '2019-07-18');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), '2019-07-19');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), '2019-07-20');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), '2019-07-21');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("8c7598b5-b623-428e-9c76-f5e52efaa801", "-",""))), '2019-07-22');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))),7, '1234', 'Jonathan', 'Sanchez', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), 'mail', 'M', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-07-23');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-07-24');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-07-25');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-07-26');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-07-27');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-07-28');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-07-29');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-07-30');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-07-31');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-08-01');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-08-02');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-08-03');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("cf285731-6844-4d5d-9cea-8fc9b6189a81", "-",""))), '2019-08-04');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("fd76bfd3-491c-494f-8f9c-ee1bc58baa60", "-",""))),8, '1234', 'Antonio', 'Fernandez', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("fd76bfd3-491c-494f-8f9c-ee1bc58baa60", "-",""))), 'mail', 'M', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("fd76bfd3-491c-494f-8f9c-ee1bc58baa60", "-",""))), '2019-08-05');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("fd76bfd3-491c-494f-8f9c-ee1bc58baa60", "-",""))), '2019-08-06');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))),9, '1234', 'Guadalupe', 'Puente', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-07');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-08');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-09');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-10');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-12');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-13');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-14');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-15');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-16');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-17');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), '2019-08-18');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("b694644f-be59-456f-8f71-14f2743030dc", "-",""))),10, '1234', 'Ferran', 'Buch', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("b694644f-be59-456f-8f71-14f2743030dc", "-",""))), 'mail', 'M', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("b694644f-be59-456f-8f71-14f2743030dc", "-",""))), '2019-08-19');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("b694644f-be59-456f-8f71-14f2743030dc", "-",""))), '2019-08-20');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("b694644f-be59-456f-8f71-14f2743030dc", "-",""))), '2019-08-21');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("b694644f-be59-456f-8f71-14f2743030dc", "-",""))), '2019-08-22');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("618dbabd-6e81-4795-8b76-2a600f65ad3d", "-",""))),11, '1234', 'Cristina', 'Fabregas', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("618dbabd-6e81-4795-8b76-2a600f65ad3d", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("618dbabd-6e81-4795-8b76-2a600f65ad3d", "-",""))), '2019-08-23');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("618dbabd-6e81-4795-8b76-2a600f65ad3d", "-",""))), '2019-08-24');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("618dbabd-6e81-4795-8b76-2a600f65ad3d", "-",""))), '2019-08-25');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("618dbabd-6e81-4795-8b76-2a600f65ad3d", "-",""))), '2019-08-26');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("618dbabd-6e81-4795-8b76-2a600f65ad3d", "-",""))), '2019-08-27');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("618dbabd-6e81-4795-8b76-2a600f65ad3d", "-",""))), '2019-08-28');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("618dbabd-6e81-4795-8b76-2a600f65ad3d", "-",""))), '2019-08-29');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("618dbabd-6e81-4795-8b76-2a600f65ad3d", "-",""))), '2019-08-30');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("174159f9-5392-4268-a2a9-d2708e9c0d86", "-",""))),12, '1234', 'Enric', 'Negre', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("174159f9-5392-4268-a2a9-d2708e9c0d86", "-",""))), 'mail', 'M', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("174159f9-5392-4268-a2a9-d2708e9c0d86", "-",""))), '2019-08-31');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("174159f9-5392-4268-a2a9-d2708e9c0d86", "-",""))), '2019-09-01');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("174159f9-5392-4268-a2a9-d2708e9c0d86", "-",""))), '2019-09-02');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("174159f9-5392-4268-a2a9-d2708e9c0d86", "-",""))), '2019-09-03');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("174159f9-5392-4268-a2a9-d2708e9c0d86", "-",""))), '2019-09-04');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("174159f9-5392-4268-a2a9-d2708e9c0d86", "-",""))), '2019-09-05');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("174159f9-5392-4268-a2a9-d2708e9c0d86", "-",""))), '2019-09-06');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("174159f9-5392-4268-a2a9-d2708e9c0d86", "-",""))), '2019-09-07');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))),13, '1234', 'Marta', 'Roig', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), '2019-09-08');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), '2019-09-09');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), '2019-09-10');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), '2019-09-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), '2019-09-12');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), '2019-09-13');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), '2019-09-14');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), '2019-09-15');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), '2019-09-16');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("86332be0-bb47-42d7-9438-bf4da4b543d4", "-",""))), '2019-09-17');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))),14, '1234', 'Jordi', 'Santacana', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), 'mail', 'M', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-18');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-19');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-20');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-21');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-22');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-23');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-24');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-25');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-26');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-27');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-28');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("abd22d73-bc5c-4d31-bcd6-b38f70883a6d", "-",""))), '2019-09-29');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("0db63fc6-307e-4801-a3b0-804e67107e53", "-",""))),15, '1234', 'Susana', 'Zaragoza', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("0db63fc6-307e-4801-a3b0-804e67107e53", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("0db63fc6-307e-4801-a3b0-804e67107e53", "-",""))), '2019-09-30');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("0db63fc6-307e-4801-a3b0-804e67107e53", "-",""))), '2019-10-01');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))),16, '1234', 'Jesús', 'Moncada', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))), 'mail', 'M', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))), '2019-10-02');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))), '2019-10-03');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))), '2019-10-04');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))), '2019-10-05');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))), '2019-10-06');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))), '2019-10-07');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))), '2019-10-08');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))), '2019-10-09');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("e5c3c615-1095-47c4-b3a9-6fb39311130d", "-",""))), '2019-10-10');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("0fee3bab-9917-4fa4-90ba-4a3aa03e3b2c", "-",""))),17, '1234', 'Mònica', 'Font', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("0fee3bab-9917-4fa4-90ba-4a3aa03e3b2c", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("0fee3bab-9917-4fa4-90ba-4a3aa03e3b2c", "-",""))), '2019-10-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("0fee3bab-9917-4fa4-90ba-4a3aa03e3b2c", "-",""))), '2019-10-12');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("0fee3bab-9917-4fa4-90ba-4a3aa03e3b2c", "-",""))), '2019-10-13');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("0fee3bab-9917-4fa4-90ba-4a3aa03e3b2c", "-",""))), '2019-10-14');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("0fee3bab-9917-4fa4-90ba-4a3aa03e3b2c", "-",""))), '2019-10-15');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("0fee3bab-9917-4fa4-90ba-4a3aa03e3b2c", "-",""))), '2019-10-16');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("17e0d6a4-1abb-4e4c-b7ac-976b1e73d4d8", "-",""))),18, '1234', 'Alberto', 'Blanco', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("17e0d6a4-1abb-4e4c-b7ac-976b1e73d4d8", "-",""))), 'mail', 'M', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("17e0d6a4-1abb-4e4c-b7ac-976b1e73d4d8", "-",""))), '2019-10-17');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("17e0d6a4-1abb-4e4c-b7ac-976b1e73d4d8", "-",""))), '2019-10-18');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("17e0d6a4-1abb-4e4c-b7ac-976b1e73d4d8", "-",""))), '2019-10-19');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("17e0d6a4-1abb-4e4c-b7ac-976b1e73d4d8", "-",""))), '2019-10-20');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("17e0d6a4-1abb-4e4c-b7ac-976b1e73d4d8", "-",""))), '2019-10-21');

INSERT INTO `student_absence` (`student`, `date`) VALUES ((UNHEX(REPLACE("17e0d6a4-1abb-4e4c-b7ac-976b1e73d4d8", "-",""))), '2019-10-22');

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("3b758092-9034-44a4-b04d-000eebd9ba3d", "-",""))),19, '1234', 'Samantha', 'Fox', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`) VALUES ((UNHEX(REPLACE("3b758092-9034-44a4-b04d-000eebd9ba3d", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11');

INSERT INTO `exercise` (`id`, `name`, `is_common`, `order`) VALUES ((UNHEX(REPLACE("19576258-61c3-4d99-8b6f-944ae7cc1f2f", "-",""))), 'Github', 1, 1);

INSERT INTO `exercise` (`id`, `name`, `is_common`, `order`) VALUES ((UNHEX(REPLACE("3b758092-9034-44a4-b04d-000eebd9ba3d", "-",""))), 'USFlights', 1, 2);

INSERT INTO `exercise` (`id`, `name`, `is_common`, `order`) VALUES ((UNHEX(REPLACE("0fee3bab-9917-4fa4-90ba-4a3aa03e3b2c", "-",""))), 'Modelatge BBDD', 1, 3);

INSERT INTO `exercise` (`id`, `name`, `is_common`, `order`, `itinerary`) VALUES ((UNHEX(REPLACE("5c718d72-7fc4-4da0-a99e-dfabb1e89cef", "-",""))), 'Disseny de funcionalitats', 0, 1, (UNHEX(REPLACE("b15345c1-7697-453e-abdb-41a470bc3566", "-",""))));

INSERT INTO `exercise` (`id`, `name`, `is_common`, `order`, `itinerary`) VALUES ((UNHEX(REPLACE("a8484815-809a-4abb-839a-72f2c78aaa51", "-",""))), 'Projecte Fase 1-2 (html-css)', 0, 2, (UNHEX(REPLACE("b15345c1-7697-453e-abdb-41a470bc3566", "-",""))));

INSERT INTO `exercise` (`id`, `name`, `is_common`, `order`, `itinerary`) VALUES ((UNHEX(REPLACE("16254c4d-bb05-4668-a715-96fef62e416f", "-",""))), 'Projecte Fase 2 (funcionalitats)', 0, 3, (UNHEX(REPLACE("b15345c1-7697-453e-abdb-41a470bc3566", "-",""))));

INSERT INTO `exercise` (`id`, `name`, `is_common`, `order`, `itinerary`) VALUES ((UNHEX(REPLACE("2b7d7475-44b6-4dc0-b3ca-cdfb96b3dda0", "-",""))), 'Lletres repetides', 0, 1, (UNHEX(REPLACE("5b077002-e07a-4731-86f7-0f85936df341", "-",""))));

INSERT INTO `exercise` (`id`, `name`, `is_common`, `order`, `itinerary`) VALUES ((UNHEX(REPLACE("a891e100-bf59-4eb5-b84e-32f2247d7e1f", "-",""))), 'Vehicles', 0, 2, (UNHEX(REPLACE("5b077002-e07a-4731-86f7-0f85936df341", "-",""))));

INSERT INTO `exercise` (`id`, `name`, `is_common`, `order`, `itinerary`) VALUES ((UNHEX(REPLACE("17e0d6a4-1abb-4e4c-b7ac-976b1e73d4d8", "-",""))), 'Videos', 0, 3, (UNHEX(REPLACE("5b077002-e07a-4731-86f7-0f85936df341", "-",""))));

INSERT INTO `user` (`id`, `username`, `password`, `name`, `surnames`, `role`) VALUES ((UNHEX(REPLACE("6a4b1a9c-5dac-4db3-ac72-d415be594b5c", "-",""))),25, '1234', 'Laura', 'Ponsa', 'STUDENT');

INSERT INTO `student` (`id`, `mail`, `sex`, `conclusion`, `start_date`, `deadline`, `itinerary`) VALUES ((UNHEX(REPLACE("6a4b1a9c-5dac-4db3-ac72-d415be594b5c", "-",""))), 'mail', 'F', 'ELIGIBLE', '2019-06-01', '2019-11-11',(UNHEX(REPLACE("b15345c1-7697-453e-abdb-41a470bc3566", "-",""))));
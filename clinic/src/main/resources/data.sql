-- Medicamentos con valores por defecto
INSERT INTO medicines (id, name, cost, default_dose, default_treatment_duration)
SELECT 'MED-001', 'Paracetamol 500 mg Tabletas', 1200.00, '500 mg cada 8 horas', '7 dias'
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE id = 'MED-001');

INSERT INTO medicines (id, name, cost, default_dose, default_treatment_duration)
SELECT 'MED-002', 'Ibuprofeno 400 mg Capsulas', 1500.00, '400 mg cada 8 horas', '5 dias'
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE id = 'MED-002');

INSERT INTO medicines (id, name, cost, default_dose, default_treatment_duration)
SELECT 'MED-003', 'Amoxicilina 500 mg', 3200.00, '500 mg cada 12 horas', '10 dias'
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE id = 'MED-003');

INSERT INTO medicines (id, name, cost, default_dose, default_treatment_duration)
SELECT 'MED-004', 'Azitromicina 500 mg', 4100.00, '500 mg cada 24 horas', '5 dias'
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE id = 'MED-004');

INSERT INTO medicines (id, name, cost, default_dose, default_treatment_duration)
SELECT 'MED-005', 'Losartan 50 mg', 2800.00, '50 mg cada 24 horas', '30 dias'
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE id = 'MED-005');

INSERT INTO medicines (id, name, cost, default_dose, default_treatment_duration)
SELECT 'MED-006', 'Metformina 850 mg', 2600.00, '850 mg cada 12 horas', '30 dias'
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE id = 'MED-006');

INSERT INTO medicines (id, name, cost, default_dose, default_treatment_duration)
SELECT 'MED-007', 'Omeprazol 20 mg', 1800.00, '20 mg cada 24 horas', '14 dias'
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE id = 'MED-007');

INSERT INTO medicines (id, name, cost, default_dose, default_treatment_duration)
SELECT 'MED-008', 'Prednisolona 5 mg', 2300.00, '5 mg cada 12 horas', '7 dias'
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE id = 'MED-008');

INSERT INTO medicines (id, name, cost, default_dose, default_treatment_duration)
SELECT 'MED-009', 'Clopidogrel 75 mg', 5200.00, '75 mg cada 24 horas', '30 dias'
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE id = 'MED-009');

INSERT INTO medicines (id, name, cost, default_dose, default_treatment_duration)
SELECT 'MED-010', 'Atorvastatina 20 mg', 4800.00, '20 mg cada 24 horas', '30 dias'
WHERE NOT EXISTS (SELECT 1 FROM medicines WHERE id = 'MED-010');

-- Procedimientos con valores por defecto
INSERT INTO procedures (id, name, cost, default_quantity, default_frequency, default_requires_specialist)
SELECT 'PROC-001', 'Curacion de Herida Menor', 35000.00, 1, 'Una vez', FALSE
WHERE NOT EXISTS (SELECT 1 FROM procedures WHERE id = 'PROC-001');

INSERT INTO procedures (id, name, cost, default_quantity, default_frequency, default_requires_specialist)
SELECT 'PROC-002', 'Sutura Simple', 52000.00, 1, 'Una vez', TRUE
WHERE NOT EXISTS (SELECT 1 FROM procedures WHERE id = 'PROC-002');

INSERT INTO procedures (id, name, cost, default_quantity, default_frequency, default_requires_specialist)
SELECT 'PROC-003', 'Curacion Avanzada con Desbridamiento', 78000.00, 3, 'Cada 48 horas', TRUE
WHERE NOT EXISTS (SELECT 1 FROM procedures WHERE id = 'PROC-003');

INSERT INTO procedures (id, name, cost, default_quantity, default_frequency, default_requires_specialist)
SELECT 'PROC-004', 'Nebulizacion', 22000.00, 2, 'Cada 12 horas', FALSE
WHERE NOT EXISTS (SELECT 1 FROM procedures WHERE id = 'PROC-004');

INSERT INTO procedures (id, name, cost, default_quantity, default_frequency, default_requires_specialist)
SELECT 'PROC-005', 'Terapia Respiratoria', 68000.00, 5, 'Diaria', TRUE
WHERE NOT EXISTS (SELECT 1 FROM procedures WHERE id = 'PROC-005');

INSERT INTO procedures (id, name, cost, default_quantity, default_frequency, default_requires_specialist)
SELECT 'PROC-006', 'Fisioterapia Postoperatoria', 90000.00, 10, 'Tres veces por semana', TRUE
WHERE NOT EXISTS (SELECT 1 FROM procedures WHERE id = 'PROC-006');

INSERT INTO procedures (id, name, cost, default_quantity, default_frequency, default_requires_specialist)
SELECT 'PROC-007', 'Electrocardiograma', 45000.00, 1, 'Una vez', FALSE
WHERE NOT EXISTS (SELECT 1 FROM procedures WHERE id = 'PROC-007');

INSERT INTO procedures (id, name, cost, default_quantity, default_frequency, default_requires_specialist)
SELECT 'PROC-008', 'Curacion de Ulcera', 65000.00, 4, 'Cada 72 horas', TRUE
WHERE NOT EXISTS (SELECT 1 FROM procedures WHERE id = 'PROC-008');

INSERT INTO procedures (id, name, cost, default_quantity, default_frequency, default_requires_specialist)
SELECT 'PROC-009', 'Lavado Gastrico', 58000.00, 1, 'Una vez', TRUE
WHERE NOT EXISTS (SELECT 1 FROM procedures WHERE id = 'PROC-009');

INSERT INTO procedures (id, name, cost, default_quantity, default_frequency, default_requires_specialist)
SELECT 'PROC-010', 'Aplicacion de Inyeccion Intramuscular', 18000.00, 1, 'Una vez', FALSE
WHERE NOT EXISTS (SELECT 1 FROM procedures WHERE id = 'PROC-010');

-- Ayudas diagnosticas con valores por defecto
INSERT INTO diagnostic_aids (id, name, cost, default_quantity, default_requires_specialist)
SELECT 'DIA-001', 'Radiografia de Torax', 75000.00, 1, TRUE
WHERE NOT EXISTS (SELECT 1 FROM diagnostic_aids WHERE id = 'DIA-001');

INSERT INTO diagnostic_aids (id, name, cost, default_quantity, default_requires_specialist)
SELECT 'DIA-002', 'Resonancia Magnetica Cerebral', 320000.00, 1, TRUE
WHERE NOT EXISTS (SELECT 1 FROM diagnostic_aids WHERE id = 'DIA-002');

INSERT INTO diagnostic_aids (id, name, cost, default_quantity, default_requires_specialist)
SELECT 'DIA-003', 'Tomografia Computarizada Abdominal', 210000.00, 1, TRUE
WHERE NOT EXISTS (SELECT 1 FROM diagnostic_aids WHERE id = 'DIA-003');

INSERT INTO diagnostic_aids (id, name, cost, default_quantity, default_requires_specialist)
SELECT 'DIA-004', 'Ecografia Obstetrica', 68000.00, 1, TRUE
WHERE NOT EXISTS (SELECT 1 FROM diagnostic_aids WHERE id = 'DIA-004');

INSERT INTO diagnostic_aids (id, name, cost, default_quantity, default_requires_specialist)
SELECT 'DIA-005', 'Electroencefalograma', 95000.00, 1, TRUE
WHERE NOT EXISTS (SELECT 1 FROM diagnostic_aids WHERE id = 'DIA-005');

INSERT INTO diagnostic_aids (id, name, cost, default_quantity, default_requires_specialist)
SELECT 'DIA-006', 'Prueba de Esfuerzo', 110000.00, 1, TRUE
WHERE NOT EXISTS (SELECT 1 FROM diagnostic_aids WHERE id = 'DIA-006');

INSERT INTO diagnostic_aids (id, name, cost, default_quantity, default_requires_specialist)
SELECT 'DIA-007', 'Laboratorio Perfil Lipidico', 38000.00, 1, FALSE
WHERE NOT EXISTS (SELECT 1 FROM diagnostic_aids WHERE id = 'DIA-007');

INSERT INTO diagnostic_aids (id, name, cost, default_quantity, default_requires_specialist)
SELECT 'DIA-008', 'Laboratorio Hemograma Completo', 28000.00, 1, FALSE
WHERE NOT EXISTS (SELECT 1 FROM diagnostic_aids WHERE id = 'DIA-008');

INSERT INTO diagnostic_aids (id, name, cost, default_quantity, default_requires_specialist)
SELECT 'DIA-009', 'Prueba PCR COVID-19', 45000.00, 1, FALSE
WHERE NOT EXISTS (SELECT 1 FROM diagnostic_aids WHERE id = 'DIA-009');

INSERT INTO diagnostic_aids (id, name, cost, default_quantity, default_requires_specialist)
SELECT 'DIA-010', 'Densitometria Osea', 125000.00, 1, TRUE
WHERE NOT EXISTS (SELECT 1 FROM diagnostic_aids WHERE id = 'DIA-010');

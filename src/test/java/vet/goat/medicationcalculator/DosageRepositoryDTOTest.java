package vet.goat.medicationcalculator;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import vet.goat.medicationcalculator.dto.DosageRange;
import vet.goat.medicationcalculator.entity.Dosage;
import vet.goat.medicationcalculator.repository.DosageRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
public class DosageRepositoryDTOTest {
    @Autowired
    private DosageRepository dao;

    @Test
    public void InvocationDAOThroughDTOImpl_ObjectTransfersNonNullData() {
        DosageRange range = dao.getDosageValue("синулокс", "NEEDLE_VEIN",
                "кошка");

        assertNotNull(range.startValue());

        assertEquals(20.0, range.startValue());

        assertNotNull(range.endValue());

        assertEquals(25.0, range.endValue());
    }
}

package fr.epsi.atlas.monnaie.service;

import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.Optional;

import fr.epsi.atlas.monnaie.repository.MonnaieRepository;

public class MonnaieServiceTest {

	@Test
	public void deleteByCode_quandLaMonnaieExiste() throws Exception {
		MonnaieRepository mockRepository = mock(MonnaieRepository.class);
		when(mockRepository.existsById("USD")).thenReturn(true);
		MonnaieService sut = new MonnaieService(mockRepository);

		sut.deleteByCode("USD");
		
		verify(mockRepository).deleteById("USD");
	}

	@Test(expected = MonnaieInexistanteException.class)
	public void getByCode_lanceUneExceptionLorsqueLeCodeNeCorrespondPasAUneMonnaie() throws Exception {
		MonnaieRepository mockRepository = mock(MonnaieRepository.class);
		MonnaieService sut = new MonnaieService(mockRepository);
		when(mockRepository.findById("USD")).thenReturn(Optional.empty());
		
		sut.getByCode("USD");
	}
}











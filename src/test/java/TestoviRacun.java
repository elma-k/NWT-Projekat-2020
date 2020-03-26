

        import com.hotel.ena.repository.RacunRepository;
        //import io.micrometer.core.instrument.util.IOUtils;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
        import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
        import org.springframework.test.context.TestPropertySource;
        import org.springframework.test.context.junit4.SpringRunner;



        import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

public class TestoviRacun {
    @Autowired
    private RacunRepository racunRepozitorij;



    @Test
    public void testPostojiRegistrovanjeRacuna() throws Exception {
        assertThat(racunRepozitorij.existsByIDRacuna("registrovanje-racuna")).isEqualTo(true);
    }


}
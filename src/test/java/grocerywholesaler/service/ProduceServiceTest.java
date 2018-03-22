package grocerywholesaler.service;

import grocerywholesaler.model.Produce;
import grocerywholesaler.model.ProducePriceOnly;
import grocerywholesaler.repository.ProduceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProduceServiceTest {

    @Spy
    @InjectMocks
    private ProduceService produceService;

    @Mock
    private ProduceRepository produceRepository;

    @Captor
    private ArgumentCaptor<Produce> produceArgumentCaptor;

    private List<Produce> produceList = new ArrayList<>();
    private List<Produce> emptyList = new ArrayList<>();

    public void setup_setData_DeletesDataBeforeSettingIfFindAllIsNotNull(){
        produceList.add(new Produce("bacon", 1.8, 20, "2014-12-30"));
        produceList.add(new Produce("grapes", 1, 10, "2014-03-30"));
    }

    @Test
    public void setData_DeletesDataBeforeSettingIfFindAllIsNotNull(){
        setup_setData_DeletesDataBeforeSettingIfFindAllIsNotNull();
        when(produceRepository.findAll()).thenReturn(produceList);
        produceService.setData(produceList);
        verify(produceRepository, times(1)).deleteAll();
        verify(produceRepository, times(1)).saveAll(produceList);
    }

    @Test
    public void setData_DoesntDeleteBecauseRepositoryEmpty(){
        when(produceRepository.findAll()).thenReturn(emptyList);
        produceService.setData(produceList);
        verify(produceRepository, never()).deleteAll();
        verify(produceRepository, times(1)).saveAll(produceList);
    }

    private Produce produce = new Produce("bacon", 1.8, 20, "2014-03-30");
    private ProducePriceOnly producePriceOnly = new ProducePriceOnly("bacon", 3.0);

    @Test
    public void updatePrice_returnsNullIfProduceNameNotFound(){
        when(produceRepository.findByName(anyString())).thenReturn(null);
        Assert.assertEquals(null, produceService.updatePrice(producePriceOnly));
        verify(produceRepository, never()).save(any());
    }

    @Test
    public void updatePrice_returnsUpdatedProduceWithNewPriceAndNewUpdatedDate(){
        when(produceRepository.findByName(anyString())).thenReturn(produce);
        produceService.updatePrice(producePriceOnly);
        verify(produceRepository, times(1)).save(any());
        verify(produceRepository).save(produceArgumentCaptor.capture());
        Produce returnedProduce = produceArgumentCaptor.getValue();
        Assert.assertTrue(returnedProduce.getPrice() == producePriceOnly.getPrice());
        Assert.assertTrue(LocalDate.parse(returnedProduce.getUpdated()).equals(LocalDate.now()));
    }

    public void setup_getProduceByUpdatedDate_order(){
        produceList.add(new Produce("bacon", 1.8, 20, "2014-12-30"));
        produceList.add(new Produce("grapes", 1, 10, "2014-03-30"));
    }

    @Test
    public void getProduceByUpdatedDate_orderASC(){
        setup_getProduceByUpdatedDate_order();
        when(produceRepository.findAllByOrderByUpdatedAsc()).thenReturn(produceList);
        Assert.assertEquals(produceList, produceService.getProduceByUpdatedDate("asc"));
        verify(produceRepository,times(1)).findAllByOrderByUpdatedAsc();
        verify(produceRepository,never()).findAllByOrderByUpdatedDesc();
    }

    @Test
    public void getProduceByUpdatedDate_orderDESC(){
        setup_getProduceByUpdatedDate_order();
        when(produceRepository.findAllByOrderByUpdatedDesc()).thenReturn(produceList);
        Assert.assertEquals(produceList, produceService.getProduceByUpdatedDate("desc"));
        verify(produceRepository,times(1)).findAllByOrderByUpdatedDesc();
        verify(produceRepository,never()).findAllByOrderByUpdatedAsc();
    }

    @Test
    public void getProduceByUpdatedDate_orderNotRecognised(){
        setup_getProduceByUpdatedDate_order();
        when(produceRepository.findAllByOrderByUpdatedDesc()).thenReturn(produceList);
        Assert.assertEquals(null, produceService.getProduceByUpdatedDate(anyString()));
        verify(produceRepository,never()).findAllByOrderByUpdatedDesc();
        verify(produceRepository,never()).findAllByOrderByUpdatedAsc();
    }

    @Test
    public void searchProduceByNameReturnsProduceWhenNameIsRecognised(){
        when(produceRepository.findByNameRegex(anyString())).thenReturn(produceList);
        Assert.assertEquals(produceList, produceService.searchProduceByName(anyString()));
    }

    @Test
    public void searchProduceByNameReturnsEmptyArrayWhenNameIsNotRecognised(){
        when(produceRepository.findByNameRegex(anyString())).thenReturn(emptyList);
        Assert.assertEquals(emptyList, produceService.searchProduceByName(anyString()));
    }
}

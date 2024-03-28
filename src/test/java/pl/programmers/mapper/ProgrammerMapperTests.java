package pl.programmers.mapper;

import org.junit.Test;
import pl.programmers.entity.Programmer;
import pl.programmers.pojo.ProgrammerDto;

import static org.junit.Assert.*;

public class ProgrammerMapperTests {
    @Test
    public void test_ProgrammerMapper_MappingIsWellDone() {
        //given
        Programmer programmer = new Programmer();
        //when
        ProgrammerDto result = ProgrammerMapper.mapToProgrammerDto(programmer);
        //then
        assertNotNull(result);
        assertEquals(programmer.getId(), result.getId());
        assertEquals(programmer.getFirstName(), result.getFirstName());
        assertEquals(programmer.getLastName(), result.getLastName());
        assertEquals(programmer.getRepoName(), result.getRepoName());
    }

    @Test
    public void test_ProgrammerMapper_NullInput(){
        //when
        ProgrammerDto result = ProgrammerMapper.mapToProgrammerDto(null);
        //then
        assertNull(result);
    }
}

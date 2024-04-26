package pl.programmers.programmer.mapper;

import org.junit.Test;
import pl.programmers.programmer.entity.Programmer;
import pl.programmers.programmer.pojo.ProgrammerDto;

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
        assertEquals(programmer.getId(), result.id());
        assertEquals(programmer.getFirstName(), result.firstName());
        assertEquals(programmer.getLastName(), result.lastName());
        assertEquals(programmer.getRepoName(), result.repoName());
    }

    @Test
    public void test_ProgrammerMapper_NullInput(){
        //when
        ProgrammerDto result = ProgrammerMapper.mapToProgrammerDto(null);
        //then
        assertNull(result);
    }
}

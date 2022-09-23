# PartialStub

I am not a fan of mocking frameworks. Too many times I have seen mocks over-used, used incorrectly or even tests that 
are just plain wrong because the developer did not understand the API. I also find that tests become over-complicated 
and difficult to understand. In the vast majority of cases a simple test implementation is sufficient and much easier 
to understand and debug.

That said however, I do sometimes need to implement/mock just a few methods on a very wide interface and this is where 
PartialStub helps. You can create an abstract class implementing just the methods you need for the test and use PartialStub 
to construct it:

    public class PartialStubExampleTest {
        @Test
        public void testSize() {
            TableModel partiallyImplementedTableModel = PartialStub.create(PartiallyImplementedTableModel.class);
            assertEquals(3, partiallyImplementedTableModel.getRowCount());
        }
    }
        
    public abstract class PartiallyImplementedTableModel implements TableModel {
        @Override
        public int getRowCount() {
            return 3;
        }
    }

public class CListTest {
  public static void main(String[] args){
      MyCList<Integer> list = new MyCList<Integer>();

      //Test Empty List:
      assert list.remove() == null;
      assert list.length() == 0;
      assert list.currPos() == -1;
      assert list.moveToPos(4) == false;
      assert list.getValue() == null;
      assert list.isAtEnd() == false;

      //Test Adding to Lists
      for (int i = 10; i > 0; i--) {
          list.insert(i);
      }
      list.moveToStart();
      assert list.getValue()==1;
      list.next();
      assert list.getValue()==2;
      list.moveToEnd();
      assert list.getValue()==9;
      list.next();
      assert list.getValue()==10;
      list.prev();
      assert list.getValue()==9;
  }
}

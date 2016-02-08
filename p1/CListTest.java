public class CListTest {
  public static void main(String[] args){
      MyCList<Integer> list = new MyCList<Integer>();
      assert list.length()==0;
      list.insert(1);
      assert list.getValue()==1;
      list.next();
      assert list.getValue()==1;
      list.insert(2);
      assert list.getValue()==2;
      list.prev();
      assert list.getValue()==1;
      list.prev();
      assert list.getValue()==2;
      list.clear();
      assert list.length()==0;
      for(int i = 1;i<10;i++) {
        list.insert(i);
      }
      for(int i = 9;i>0;i--){
        assert list.getValue()==i;
        list.next();
      }
      assert list.getValue()==9;
      list.moveToPos(4);
      assert list.getValue()==5;
      assert list.remove()==5;
      assert list.getValue()==4;
      list.append(100);
      list.prev();
      list.prev();
      assert list.getValue()==100;
  }
}

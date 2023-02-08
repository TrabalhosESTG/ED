package API;

public class Teste{
    public static void main(String[] args){
        Map map = new Map();
        map.addLocal( new Local( 123, "type", "name", 12, 12,111, 5) );
        System.out.println(map.getCount());
        //System.out.println(map.findLocalById(123).getName());
        // System.out.println(map.getLocals()[0].getName());
    }
}

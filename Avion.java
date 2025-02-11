public interface Transport{
      void deplacer();
}
    

public class Avion implements Transport {
    public void deplacer(){{
        System.out.println("L'avion se déplace");
    }}
}

public class Voiture implements Transport {
    public void deplacer(){
        System.out.println("La voiture se déplace");
    }

}
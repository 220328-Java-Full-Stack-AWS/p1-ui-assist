package revagenda.exceptions;

public class Myexceptions extends RuntimeException{

    //public UsernameOrPasswordIncorrectException(){
    //        super("The users provided username or password do not match those stored in the database");
    //    }
    //
    //    public UsernameOrPasswordIncorrectException(String message){
    //        super(message);
    //    }

public Myexceptions(){
    super("This id has been taken");
}

public Myexceptions(String message){
    super(message);
}

}

package softuni.constants;

public interface Config {
    //Web Security Config
    String FormLoginPage = "/login";
    String UsernameParameter = "email";
    String PasswordParameter = "password";
    String AccessDeniedPage = "/error/403";
    String AuthEntryPoint = "/users/login";

    //Web Socket Config
    String AppEndPoint = "/game";
    String AppDestinationPrefix = "/app/";
    String CharacterBrokerPrefix = "/character/";
    String ChatBrokerPrefix = "/chat/";

    //Socket Service Config
    String CharacterMoneyDestination = CharacterBrokerPrefix + "money";
    String CharacterInfoDestination = CharacterBrokerPrefix + "info";
}

public class MainLoginTester {
    public static void main(String[] args) throws Exception {
        String userName = "Fulano da Silva Castro Pereira", userType = "Terceiro";
        String userLogin = LoginGeneratorFixed.DefineUserID(userName, userType);
        System.out.println(userLogin + " Tamanho: " + userLogin.length());
    }
}

package seniorcare.crudseniorcare;

public class Validation{
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    // Função para validar uma senha (pelo menos 8 caracteres, contendo letras e números)
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
    }

    // Função para validar um nome de usuário (pelo menos 4 caracteres, somente letras e números)
    public static boolean isValidUsername(String username) {
        return username != null && username.length() >= 4 && username.matches("[a-zA-Z0-9]+");
    }


}

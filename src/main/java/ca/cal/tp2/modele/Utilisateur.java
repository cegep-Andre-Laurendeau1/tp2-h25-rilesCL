package ca.cal.tp2.modele;

abstract class Utilisateur {
    private int userId;
    private String nom;
    private String email;
    private String phoneNumber;

    public Utilisateur(int userId, String nom, String email, String phoneNumber) {
        this.userId = userId;
        this.nom = nom;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Utilisateur() {
        }

        public void login() {
            if (userId > 0 && nom != null && email != null) {
                notifyLogin();
            }
        }

        public String getNom () {
            return nom;
        }
        public void setNom (String nom){
            this.nom = nom;
        }
        public String getEmail () {
            return email;
        }
        public void setEmail (String email){
            this.email = email;
        }
        public String getPhoneNumber () {
            return phoneNumber;
        }
        public void setPhoneNumber (String phoneNumber){
            this.phoneNumber = phoneNumber;
        }
        public int getUserId () {
            return userId;
        }
        public void setUserId (int userId){
        this.userId = userId;
        }



    private void notifyLogin() {
        String loginInfo = String.format("ID: %d, Name: %s", userId, nom);

    }
}


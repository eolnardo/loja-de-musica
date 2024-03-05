package model;

public class Admin {


        private String nome;

        private String email;

        private String senha;

        private String status;

        private String telefone;

        private String admId;

        private String grupo;

        public Admin(String nome, String email, String senha, String status, String telefone, String admId) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.status = status;
            this.telefone = telefone;
            this.admId = admId;
        }

        public Admin(String email, String senha) {
            this.email = email;
            this.senha = senha;
        }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getAdmId() {
            return admId;
        }

        public void setAdmId(String admId) {
            this.admId = admId;
        }

        @Override
        public String toString() {
            return "Adm{" +
                    "nome='" + nome + '\'' +
                    ", email='" + email + '\'' +
                    ", senha='" + senha + '\'' +
                    ", telefone='" + telefone + '\'' +
                    ", clienteId='" + admId + '\'' +
                    ", status='" + status + '\'' +
                    ", grupo='" + grupo + '\'' +
                    '}';
        }

}

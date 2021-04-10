package com.example.takedelivery.model;

import com.example.takedelivery.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

import com.google.firebase.database.Exclude;


public class Cliente implements Serializable {

        private String ID;
        private String Nome;
        private String Email;
        private String Senha;
        private String telefone;
        private String endereco;
        private String bairro;
        private String cidade;

        public Cliente() {
        }


        public void salvarCliente() {

            DatabaseReference firebaseRef = FirebaseOptions.getFirebase();
            DatabaseReference usuario = firebaseRef.child("Clientes").child(getID());
            usuario.setValue(this);
        }

        @Exclude
        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getNome() {
            return Nome;
        }

        public void setNome(String nome) {
            Nome = nome;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getSenha() {
            return Senha;
        }

        public void setSenha(String senha) {
            Senha = senha;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

}

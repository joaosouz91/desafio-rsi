package br.com.brasilprev.api.service;

import org.springframework.stereotype.Service;

@Service
public class PublicDataValidationService {

    private static final int[] CPF_WEIGHT = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] CNPJ_WEIGHT = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public boolean isValidCpfCnpj(String cpfCnpj) {
        return (isValidCPF(cpfCnpj) || isValidCNPJ(cpfCnpj));
    }

    private int calculateDigit(String str, int[] weight) {
        int sum = 0;
        for (int index=str.length()-1, digit; index >= 0; index-- ) {
            digit = Integer.parseInt(str.substring(index,index+1));
            sum += digit*weight[weight.length-str.length()+index];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    private String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }

    private boolean isValidCPF(String cpf) {
        cpf = cpf.trim().replace(".", "").replace("-", "");
        if ((cpf==null) || (cpf.length()!=11)) return false;

        for (int j = 0; j < 10; j++)
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                return false;

        Integer digitOne = calculateDigit(cpf.substring(0,9), CPF_WEIGHT);
        Integer digitTwo = calculateDigit(cpf.substring(0,9) + digitOne, CPF_WEIGHT);
        return cpf.equals(cpf.substring(0,9) + digitOne.toString() + digitTwo.toString());
    }

    private boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.trim().replace(".", "").replace("-", "");
        if ((cnpj==null)||(cnpj.length()!=14)) return false;

        Integer digitOne = calculateDigit(cnpj.substring(0,12), CNPJ_WEIGHT);
        Integer digitTwo = calculateDigit(cnpj.substring(0,12) + digitOne, CNPJ_WEIGHT);
        return cnpj.equals(cnpj.substring(0,12) + digitOne.toString() + digitTwo.toString());
    }

}

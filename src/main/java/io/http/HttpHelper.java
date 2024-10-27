package io.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.CalcParams;
import data.Target;
import data.dto.AnnualCalcResultDTO;
import data.dto.CalcResultDTO;
import data.dto.CalcParamsDTO;
import data.dto.DiffCalcResultDTO;
import data.enums.PaymentType;
import date.Date;
import exeptions.CalcErrorException;
import org.apache.logging.log4j.LogManager;
import util.valueExtractor.ValueExtractor;
import util.valueExtractor.Values;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static util.valueExtractor.Extractions.*;
import static data.enums.PaymentType.*;

public class HttpHelper {

    public static CalcParams getCalcParamsFromBody(InputStream body) {
        CalcParamsDTO dto = deserializeRequestBody(body);
        return getCalcParams(dto);
    }

    @Values
    public static byte[] serializeResponseBody(Target target) {
        ValueExtractor.init(target);

        CalcResultDTO dto;

        if (PAYMENT_TYPE() == ANNUAL) {
            AnnualCalcResultDTO annualDto = new AnnualCalcResultDTO();
            annualDto.setPaymentAmount(PAYMENT_AMOUNT());
            dto = annualDto;
        } else {
            DiffCalcResultDTO diffDto = new DiffCalcResultDTO();
            diffDto.setMaxPaymentAmount(MAX_PAYMENT());
            diffDto.setMinPaymentAmount(MIN_PAYMENT());
            dto = diffDto;
        }

        dto.setId(ID());
        dto.setFullCreditAmount(FULL_CREDIT_AMOUNT());
        dto.setClearCreditAmount(CLEAR_CREDIT_AMOUNT());
        dto.setTerm(TERM());
        dto.setRate(RATE());
        dto.setInsuranceAmount(INSURANCE_AMOUNT());
        dto.setPayments(target.getCalcResult().getPaymentList().toArray(new String[0]));
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsBytes(dto);
        } catch (JsonProcessingException e) {
            LogManager.getLogger(HttpHelper.class.getName()).error("Ошибка преобразования объекта с id" + dto.getId() + Arrays.toString(e.getStackTrace()));
            throw new CalcErrorException();
        }
    }

    private static CalcParamsDTO deserializeRequestBody(InputStream body) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(body, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int buffer;
            StringBuilder stringBuilder = new StringBuilder(512);

            while ((buffer = bufferedReader.read()) != -1) {
                stringBuilder.append((char) buffer);
            }

            bufferedReader.close();
            inputStreamReader.close();

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(stringBuilder.toString(), CalcParamsDTO.class);
        } catch (IOException e) {
            LogManager.getLogger(HttpHelper.class.getName()).error("Ошибка десериализации объекта");
            throw new CalcErrorException();
        }
    }

    private static CalcParams getCalcParams(CalcParamsDTO query) {
        CalcParams calcParams = new CalcParams();
        calcParams.setPaymentType(PaymentType.valueOf(query.getPaymentType()));
        calcParams.setInsurance(query.isInsurance());
        calcParams.setInsuranceIncluding(query.isInsuranceIncluding());
        calcParams.setInsuranceK(query.getInsuranceK());
        calcParams.setAmount(query.getAmount());
        calcParams.setTerm(query.getTerm());
        calcParams.setRate(query.getRate());
        calcParams.setStartDate(new Date(query.getStartDate()));
        return calcParams;
    }
}

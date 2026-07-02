package com.wu.billersplus.connector.cashin.pago.efectivo.online.dto;

import com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.DeudaParameters;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.DateUtils;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.utils.Decimal;
import com.wu.billersplus.entities.BillersPlusRequest;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Map;

import static com.wu.billersplus.connector.cashin.pago.efectivo.online.entities.ParametrosConector.OPERATION_REVERSA;

public class ReversaRequestDTO extends RequestTrxDTO implements Serializable {

    private static final long serialVersionUID = 8118313193679295396L;

    public ReversaRequestDTO(BillersPlusRequest request, Map<String, String> dataAdic, Map<String, String> parametros) {
        super(request,parametros);

        DateTime dateTimeZone = DateUtils.getDataTimeZone(request.getFechaHoraFe(), request.getHusoHorario());

        this.setCodOperation(OPERATION_REVERSA);
        this.setAmount(Decimal.unformat(request.getImporte(),Decimal.SCALE_2DEC));

        if(dataAdic == null || (dataAdic != null && dataAdic.get(DeudaParameters.PARAM_SEQUENCE) == null && dataAdic.get(DeudaParameters.PARAM_COD_TRX) == null)){
            this.setSequence(getSequence(request.getIdTransaccionFe()));
            this.setOperationNumber(getCodTRX(this.getSequence(), this.getMachine(), dateTimeZone));
        }else{
            this.setSequence(dataAdic.get(DeudaParameters.PARAM_SEQUENCE));
            this.setOperationNumber(dataAdic.get(DeudaParameters.PARAM_COD_TRX));
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" importe: ").append(this.getAmount());
        sb.append(" nroSecuencia: ").append(this.getSequence());
        sb.append(" codTrx: ").append(this.getOperationNumber());
        sb.append(" codOperacion: ").append(this.getCodOperation());
        return sb.toString();
    }
}

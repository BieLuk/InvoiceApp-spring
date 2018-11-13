package pl.edu.wat.wcy.invoice.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response {
    private List<String> errors;

    public Response() { this.errors = new ArrayList<>(); }
}

package pl.edu.wat.wcy.invoice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListResponse<T> extends Response {
    private List<T> data;
}

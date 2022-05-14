package pw.pcshop.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiException {
    private final String message;
}

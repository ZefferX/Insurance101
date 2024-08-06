package com.example.insurance.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

public record ExternalClientResponseList(List<ExternalClientRequest> externalClientRequestList)  {

}


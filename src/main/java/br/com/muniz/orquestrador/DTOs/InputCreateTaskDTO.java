package br.com.muniz.orquestrador.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record InputCreateTaskDTO(
        @NotBlank(message = "O nome não pode estar vazio")
        String name,

        String description,

        @NotBlank(message = "O cron não pode estar vazio")
        String cron,

        @NotNull(message = "O status ativo não pode ser nulo")
        Boolean active,

        @NotBlank
        @URL(protocol = "http", message = "O formato da URL é inválido")
        String url
)
{ }

package com.api.board.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigurationProperties(prefix = "file")
@Getter @Setter @ToString
public class FileProperties {

	private String uploadDirectory;
}
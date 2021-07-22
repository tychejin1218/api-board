package com.api.board.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel
@XmlRootElement(name = "boards")
@XmlType(propOrder = { "boards", "boardFiles"})
@Getter
@Setter
@ToString
public class Boards {

	@ApiModelProperty(value = "게시글 목록")
	private List<Board> boards;
	@ApiModelProperty(value = "게시글 첨부파일 목록")
	private List<BoardFile> boardFiles;
}
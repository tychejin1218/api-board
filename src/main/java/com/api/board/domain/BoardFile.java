package com.api.board.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel
@XmlRootElement(name = "boardFile")
@XmlType(propOrder = { "board_seq", "file_no", "file_name_key", "file_name", "file_path", "file_size", "remark", "del_yn", "ins_user_id", "ins_date", "upd_user_id", "upd_date" })
@Getter
@Setter
@ToString
public class BoardFile {
	
	@ApiModelProperty(value = "게시글 번호")
	int board_seq;
	@ApiModelProperty(value = "첨부파일 번호")
    int file_no;
    @ApiModelProperty(value = "첨부파일 암호화명")
    String file_name_key;
    @ApiModelProperty(value = "첨부파일명")
    String file_name;
    @ApiModelProperty(value = "첨부파일 경로")
    String file_path;
    @ApiModelProperty(value = "첨부파일 크기")
    String file_size;
    @ApiModelProperty(value = "비고")
    String remark;
    @ApiModelProperty(value = "첨부파일 삭제 유무")
    String del_yn;
    @ApiModelProperty(value = "첨부파일 입력자 ID")
    String ins_user_id;
    @ApiModelProperty(value = "첨부파이 입력 일시")
    String ins_date;
    @ApiModelProperty(value = "첨부파일 수정자 ID")
    String upd_user_id;
    @ApiModelProperty(value = "첨부파일 수정 일시")
    String upd_date;
}
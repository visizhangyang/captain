
package com.porn.client.workrobot.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class WorkrobotQueryDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof WorkrobotQueryDTO;
    }



    /* 14 */
    protected WorkrobotQueryDTO(WorkrobotQueryDTOBuilder<?, ?> b) {
        super(b);
    }

    public static WorkrobotQueryDTOBuilder<?, ?> builder() {
        return new WorkrobotQueryDTOBuilderImpl();
    }

    private static final class WorkrobotQueryDTOBuilderImpl extends WorkrobotQueryDTOBuilder<WorkrobotQueryDTO, WorkrobotQueryDTOBuilderImpl> {
        protected WorkrobotQueryDTOBuilderImpl self() {
            return this;
        }

        private WorkrobotQueryDTOBuilderImpl() {
        }

        public WorkrobotQueryDTO build() {
            return new WorkrobotQueryDTO(this);
        }
    }

    public static abstract class WorkrobotQueryDTOBuilder<C extends WorkrobotQueryDTO, B extends WorkrobotQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public WorkrobotQueryDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/workrobot/dto/WorkrobotQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
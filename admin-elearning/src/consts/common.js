export const ACCESS_TOKEN = 'admin_token';

export const commonProps = {

    createFormData : (object, file) => {
        let json = JSON.stringify({
            ...object,
          });
        let blob = new Blob([json], {
          type: "application/json",
        });
    
        let formData = new FormData();
        formData.append(
          "file",
          file
        );
        formData.append("dto",blob, {contentType : "application/json"});
    
        return formData;
    },

    defaultPaginate:  {
        pageIndex: 1,
        pageSize: 10
    },

    colWillDivideFour : {
        xs: 8,
        sm: 16,
        md: 24,
        lg: 32,
    },
    colWillDivideTwo : {
        xs: 8,
        sm: 8,
        md: 12,
        lg: 12,
    },
    colWillDivideOne : {
        xs: 8,
        sm: 8,
        md: 12
    },
    rowGutterForColTwo : {
        gutter : 100
    },
    rule : {
        required : (message) => {
            return {
                required : true,
                message : message
            }
        }
    },
    formatCurrency : (number) => {
        return number.toLocaleString('en-US', { style: 'currency', currency: 'VND' });
    }
}

//f
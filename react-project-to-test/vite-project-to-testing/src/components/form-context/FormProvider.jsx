import React, { createContext, useState } from 'react';
import { Form } from 'antd';


export const FormContext = createContext();


const FormProvider = ({ children }) => {
  const [form1] = Form.useForm();
  const [form2] = Form.useForm();
  const [form3] = Form.useForm();

  return (
    <FormContext.Provider value={{
      form1,
      form2,
      form3
    }}>
      {children}
    </FormContext.Provider>
  );
};

export default FormProvider;
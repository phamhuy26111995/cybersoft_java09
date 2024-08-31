import React, { useContext } from "react";
import { Form, Input, InputNumber, Button } from "antd";
import FormProvider, { FormContext } from "../form-context/FormProvider";

const FormOne = ({ form, onFinish }) => {
  return (
    <Form form={form} onFinish={onFinish}>
      <Form.Item name="username" label="Username" rules={[{ required: true }]}>
        <Input />
      </Form.Item>
      <Form.Item name="password" label="Password" rules={[{ required: true }]}>
        <Input.Password />
      </Form.Item>
    </Form>
  );
};

const FormTwo = ({ form, onFinish }) => (
  <Form form={form} onFinish={onFinish}>
    <Form.Item name="age" label="Age" rules={[{ required: true }]}>
      <InputNumber />
    </Form.Item>
    <Form.Item name="fullname" label="Full Name" rules={[{ required: true }]}>
      <Input />
    </Form.Item>
  </Form>
);

const FormThree = ({ form, onFinish }) => (
  <Form form={form} onFinish={onFinish}>
    <Form.Item name="address" label="Address" rules={[{ required: true }]}>
      <Input />
    </Form.Item>
    <Form.Item name="job" label="Job" rules={[{ required: true }]}>
      <Input />
    </Form.Item>
  </Form>
);

const MultipleFormAntDesign = () => {
  return (
    <FormProvider>
      <FormWrapper />
    </FormProvider>
  );
};

function FormWrapper() {
  const { form1, form2, form3 } = useContext(FormContext);

  const handleSubmit = async () => {
    try {
      const values1 = await form1.validateFields();
      const values2 = await form2.validateFields();
      const values3 = await form3.validateFields();
      const allValues = {
        ...values1,
        ...values2,
        ...values3,
      };
      console.log("All form values:", allValues);
      // Ở đây bạn có thể xử lý dữ liệu tổng hợp từ cả 3 form
    } catch (error) {
      console.error("Validation failed:", error);
    }
  };



  return (
    <>
      <FormOne form={form1} />
      <FormTwo form={form2} />
      <FormThree form={form3} />

      <Button onClick={handleSubmit}>Submit All Forms</Button>
    </>
  );
}

export default MultipleFormAntDesign;

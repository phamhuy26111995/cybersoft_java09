import React, { useState, useMemo } from 'react';

const SimpleSelect = () => {
  const [selectedValue, setSelectedValue] = useState('');

  const options = useMemo(() => {
    const alphabet = Array.from({ length: 26 }, (_, i) => String.fromCharCode(97 + i));
    const numbers = Array.from({ length: 10 }, (_, i) => i.toString());
    return [...alphabet, ...numbers];
  }, []);

  const handleChange = (event) => {
    setSelectedValue(event.target.value);
  };

  return (
    <div>
      <select value={selectedValue} onChange={handleChange}>
        {options.map((option) => (
          <option key={option} value={option}>
            {option}
          </option>
        ))}
      </select>
      <p>Giá trị đã chọn: {selectedValue}</p>
    </div>
  );
};

export default SimpleSelect;
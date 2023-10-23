import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

const initialState = {
  users: [],
  defaultList: [],
  inputList: [],
  rowKey: 0,
};

export const fetchUsers = createAsyncThunk("user/fetchUsers", async () => {
  const response = await fetch("/fake_data/mini_users.json");
  const data = await response.json();

  return data;
});

export const fetchPagingUsers = createAsyncThunk(
  "user/fetchPagingUsers",
  async (paging) => {
    let response = await fetch("/fake_data/users.json");
    let data = await response.json();
    const startIndex = (paging.pageNumber - 1) * paging.itemsPerPage;
    const endIndex = startIndex + paging.itemsPerPage;

    if (paging.searchCondition) {
      let searchResult = data.filter((el) =>
        el.first_name.includes(paging.searchCondition)
      );
      return searchResult.slice(startIndex, endIndex);
    }

    let result = data.slice(startIndex, endIndex);

    return result;
  }
);

export const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    setRowKey(state, { payload }) {
      state.rowKey = payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchUsers.fulfilled, (state, action) => {
        state.users = action.payload || [];
      })
      .addCase(fetchPagingUsers.fulfilled, (state, action) => {
        state.users = action.payload || [];
      });
  },
});

export const { setRowKey } = userSlice.actions;

export default userSlice.reducer;

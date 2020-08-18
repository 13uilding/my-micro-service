import { axiosClient } from "../axios";

export async function getAllRoutines() {

  try {
    let response = await axiosClient.get("/question/routine");
    return response.data;
  } catch(e) {
    console.error(e);
    throw(e);
  }
}
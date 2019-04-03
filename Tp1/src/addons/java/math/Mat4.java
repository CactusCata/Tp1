package addons.java.math;

public class Mat4 {

	public float[][] matrix = new float[4][4];

	public static Mat4 identity() {
		Mat4 result = new Mat4();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				result.matrix[x][y] = 0f;
			}
		}

		result.matrix[0][0] = 1;
		result.matrix[1][1] = 1;
		result.matrix[2][2] = 1;
		result.matrix[3][3] = 1;

		return result;
	}

	public Mat4 mul(Mat4 m) {
		Mat4 result = identity();

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				result.matrix[x][y] = matrix[x][0] * m.matrix[0][y] + matrix[x][1] * m.matrix[1][y]
						+ matrix[x][2] * m.matrix[2][y] + matrix[x][3] * m.matrix[3][y];
			}
		}

		return result;
	}

	public static Mat4 translate(float x, float y, float z) {
		Mat4 result = identity();

		result.matrix[0][3] = x;
		result.matrix[1][3] = y;
		result.matrix[2][3] = z;

		return result;
	}

	public static Mat4 translate(Vec3 pos) {
		return translate(pos.x, pos.y, pos.z);
	}

	public static Mat4 rotate(float x, float y, float z) {
		Mat4 rx = identity();
		Mat4 ry = identity();
		Mat4 rz = identity();

		x = Mathf.toRadians(x);
		y = Mathf.toRadians(y);
		z = Mathf.toRadians(z);

		float xcos = Mathf.cos(x);
		float xsin = Mathf.sin(x);
		float ycos = Mathf.cos(y);
		float ysin = Mathf.sin(y);
		float zcos = Mathf.cos(z);
		float zsin = Mathf.sin(z);

		rx.matrix[1][1] = xcos;
		rx.matrix[1][2] = -xsin;
		rx.matrix[2][1] = xsin;
		rx.matrix[2][2] = xcos;

		ry.matrix[0][0] = ycos;
		ry.matrix[0][2] = -ysin;
		ry.matrix[2][0] = ysin;
		ry.matrix[2][2] = ycos;

		rz.matrix[0][0] = zcos;
		rz.matrix[0][1] = -zsin;
		rz.matrix[1][0] = zsin;
		rz.matrix[1][2] = zcos;

		return rz.mul(ry.mul(rx));
	}

	public static Mat4 rotate(Vec3 forward, Vec3 right, Vec3 up) {
		Mat4 result = identity();

		Vec3 f = new Vec3(forward).normalize();
		Vec3 r = new Vec3(right).normalize();
		Vec3 u = new Vec3(up).normalize();

		result.matrix[0][0] = r.x;
		result.matrix[0][1] = r.y;
		result.matrix[0][2] = r.z;

		result.matrix[1][0] = u.x;
		result.matrix[1][1] = u.y;
		result.matrix[1][2] = u.z;

		result.matrix[2][0] = f.x;
		result.matrix[2][1] = f.y;
		result.matrix[2][2] = f.z;

		return result;
	}

	public static Mat4 rotate(Vec3 forward, Vec3 up) {
		Mat4 result = identity();

		Vec3 f = new Vec3(forward).normalize();
		Vec3 r = new Vec3(up).normalize();
		r = r.cross(f);
		Vec3 u = f.cross(r);

		result.matrix[0][0] = r.x;
		result.matrix[0][1] = r.y;
		result.matrix[0][2] = r.z;

		result.matrix[1][0] = u.x;
		result.matrix[1][1] = u.y;
		result.matrix[1][2] = u.z;

		result.matrix[2][0] = f.x;
		result.matrix[2][1] = f.y;
		result.matrix[2][2] = f.z;

		return result;
	}

	public static Mat4 scale(float x, float y, float z) {
		Mat4 result = identity();

		result.matrix[0][0] = x;
		result.matrix[1][1] = y;
		result.matrix[2][2] = z;

		return result;
	}

	public static Mat4 scale(Vec3 s) {
		return scale(s.x, s.y, s.z);
	}

	public static Mat4 perspective(float fov, float aspect, float zNear, float zFar) {
		Mat4 result = identity();

		float FOV = Mathf.tan(Mathf.toRadians(fov / 2));
		float dist = zNear - zFar;

		result.matrix[0][0] = 1.0f / (FOV * aspect);
		result.matrix[1][1] = 1.0f / FOV;

		result.matrix[2][2] = (-zNear - zFar) / dist;
		result.matrix[2][3] = 2 * zFar * zNear / dist;

		result.matrix[3][2] = 1;
		result.matrix[3][3] = 0;

		return result;
	}

	public static Mat4 orthographic(float right, float left, float top, float bottom, float zNear, float zFar) {
		Mat4 result = identity();

		result.matrix[0][0] = 2 / (right - left);
		result.matrix[0][3] = -(right + left) / (right - left);

		result.matrix[1][1] = 2 / (top - bottom);
		result.matrix[1][3] = -(top + bottom) / (top - bottom);

		result.matrix[2][2] = -2 / (zFar - zNear);
		result.matrix[2][3] = -(zFar + zNear) / (zFar - zNear);

		return result;
	}

	public static Vec3 transform(Mat4 m, Vec3 v) {
		return new Vec3(m.matrix[0][0] * v.x + m.matrix[0][1] * v.y + m.matrix[0][2] * v.z + m.matrix[0][3],
				m.matrix[1][0] * v.x + m.matrix[1][1] * v.y + m.matrix[1][2] * v.z + m.matrix[0][3],
				m.matrix[2][0] * v.x + m.matrix[2][1] * v.y + m.matrix[2][2] * v.z + m.matrix[0][3]);
	}

}

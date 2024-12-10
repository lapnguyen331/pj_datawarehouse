from flask import Flask, render_template, request, redirect, url_for, flash
import pymysql

app = Flask(__name__, template_folder='proccess_manager')
app.secret_key = "your_secret_key"

# Cấu hình database
db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': 'rooot',  # Thay bằng mật khẩu của bạn
    'database': 'dbControl'
}

# Kết nối database
def get_db_connection():
    return pymysql.connect(
        host=db_config['host'],
        user=db_config['user'],
        password=db_config['password'],
        database=db_config['database'],
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor
    )

@app.route('/')
def index():
    connection = get_db_connection()
    with connection.cursor() as cursor:
        cursor.execute("SELECT * FROM proccesscontrol")
        processes = cursor.fetchall()
    connection.close()
    return render_template('index.html', processes=processes)

@app.route('/add_process', methods=['POST'])
def add_process():
    config_id = request.form['config_id']
    name = request.form['name']
    description = request.form['description']
    try:
        connection = get_db_connection()
        with connection.cursor() as cursor:
            sql = """
                INSERT INTO proccesscontrol (config_id, name, description, status, action)
                VALUES (%s, %s, %s, 'Pending', 'None')
            """
            cursor.execute(sql, (config_id, name, description))
        connection.commit()
        flash("Process added successfully!", "success")
    except Exception as e:
        flash(f"Error: {e}", "danger")
    finally:
        connection.close()
    return redirect(url_for('index'))

@app.route('/update_status/<int:process_id>', methods=['POST'])
def update_status(process_id):
    status = request.form['status']
    action = request.form['action']
    try:
        connection = get_db_connection()
        with connection.cursor() as cursor:
            sql = """
                UPDATE proccesscontrol 
                SET status = %s, action = %s, update_at = NOW()
                WHERE id = %s
            """
            cursor.execute(sql, (status, action, process_id))
        connection.commit()
        flash("Process updated successfully!", "success")
    except Exception as e:
        flash(f"Error: {e}", "danger")
    finally:
        connection.close()
    return redirect(url_for('index'))

@app.route('/delete/<int:process_id>', methods=['GET'])
def delete_process(process_id):
    try:
        connection = get_db_connection()
        with connection.cursor() as cursor:
            sql = "DELETE FROM proccesscontrol WHERE id = %s"
            cursor.execute(sql, (process_id,))
        connection.commit()
        flash("Process deleted successfully!", "success")
    except Exception as e:
        flash(f"Error: {e}", "danger")
    finally:
        connection.close()
    return redirect(url_for('index'))

if __name__ == '__main__':
    app.run(debug=True)
